package mcs.mcsfinal2100005222.Infrastructure.mysql.adapters;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import mcs.mcsfinal2100005222.Domain.dto.favorites.requests.AddToFavoritesRequest;
import mcs.mcsfinal2100005222.Domain.dto.favorites.responses.AddToFavoritesResponse;
import mcs.mcsfinal2100005222.Domain.dto.product.requests.CreateNewProductRequest;
import mcs.mcsfinal2100005222.Domain.dto.product.requests.EditProductRequest;
import mcs.mcsfinal2100005222.Domain.dto.product.responses.MainPageProductResponse;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductCategoryEntity;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Domain.ports.ProductServicePort;
import mcs.mcsfinal2100005222.Domain.security.concretes.JwtManager;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductCategory;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductEAV;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductFavorites;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.User;
import mcs.mcsfinal2100005222.Domain.utils.modelmapper.ModelMapperService;
import mcs.mcsfinal2100005222.Infrastructure.mysql.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceAdapter implements ProductServicePort {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ModelMapperService modelMapper;
    @Autowired
    private JwtManager jwtManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductFavoritesRepository productFavoritesRepository;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductEAVRepository productEAVRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public GenericResponse<List<ProductEntity>> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductEntity> productListWillBeReturned = productList.stream()
                .map(product ->
                    modelMapper.forResponse().map(product, ProductEntity.class)
                )
                .toList();
        return new GenericResponse<List<ProductEntity>>(200,productListWillBeReturned);
    }

    @Override
    public GenericResponse<Page<MainPageProductResponse>> getMainPageProducts(Pageable pageable) {
        Page<Product> allProductPages = productRepository.findAll(pageable);
        Page<MainPageProductResponse> mainPageProductResponses = allProductPages.map((eachProduct) -> {
            User user = userRepository.findById(eachProduct.getProductSellerUserUUID())
                    .orElseThrow(() -> new RuntimeException("User not found with UUID: " + eachProduct.getProductSellerUserUUID()));
            return new MainPageProductResponse(
                    eachProduct.getProductUuid(),
                    eachProduct.getProductName(),
                    eachProduct.getProductMediaURLs(),
                    eachProduct.getProductReviewScore(),
                    user.getName(),
                    eachProduct.getProductSellerUserUUID(),
                    eachProduct.getProductPrice()
            );
        });
        return new GenericResponse<Page<MainPageProductResponse>>(200,mainPageProductResponses);
    }


    @Transactional
    public GenericResponse<Product> addNewProduct(CreateNewProductRequest createNewProductRequest){
        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(createNewProductRequest.getJwt()));
        user.orElseThrow(RuntimeException::new);
        //Mapping general data
        ProductEntity productEntity = modelMapper.forRequest().map(createNewProductRequest, ProductEntity.class);
        //Setting up productUUID
        //Setting up productSellerUserUUID
        productEntity.setProductSellerUserUUID(user.get().getUserUuid());
        //Setting up product categories from productCategoryIds
        productEntity.setCategories(this.getProductCategories(createNewProductRequest.getProductCategories()).stream().map(
                productCategory -> modelMapper.forResponse().map(productCategory, ProductCategoryEntity.class)).collect(Collectors.toList()));
        List<ProductEAV> productEAVList = productEntity.getProductEAVList().stream().map(
                eachEAVDomainEntity -> modelMapper.forResponse().map(eachEAVDomainEntity,ProductEAV.class)).toList();
        List<ProductCategory> dbCategories = productCategoryRepository.findByCategoryIdIn(createNewProductRequest.getProductCategories());
        System.out.println(productEntity);
        Product productDbEntity = modelMapper.forResponse().map(productEntity,Product.class);
        for(ProductCategory eachCategory: dbCategories){
            eachCategory.getProduct().add(productDbEntity);
            eachCategory.setProduct(eachCategory.getProduct());
        }
        productDbEntity.setProductCategories(dbCategories);
        productRepository.save(productDbEntity);
        productCategoryRepository.saveAll(dbCategories);
        return new GenericResponse<Product>(200,productDbEntity);
    }

    @Transactional
    public GenericResponse<Product> editProduct(EditProductRequest editProductRequest){
        Product productToUpdate = productRepository.getProductByProductUuid(editProductRequest.getProductUuid());
        productToUpdate.setProductDescription(editProductRequest.getProductDescription());
        productToUpdate.setProductName(editProductRequest.getProductName());
        if(editProductRequest.getProductPrice() != null){
            productToUpdate.setProductPrice(editProductRequest.getProductPrice());
        }
        if(editProductRequest.getProductStockQuantity() != null){
            productToUpdate.setProductStockQuantity(editProductRequest.getProductStockQuantity());
        }
        if(editProductRequest.getProductMediaURLs() != null && !editProductRequest.getProductMediaURLs().isEmpty()){
            List<String> unitedList = new ArrayList<>();
            unitedList.addAll(productToUpdate.getProductMediaURLs());
            unitedList.addAll(editProductRequest.getProductMediaURLs());
            productToUpdate.setProductMediaURLs(unitedList);
        }
        if(editProductRequest.getIsProductPublished() != null){
            productToUpdate.setProductPublished(editProductRequest.getIsProductPublished());
        }

        productToUpdate.setProductUuid(editProductRequest.getProductUuid());
        productRepository.save(productToUpdate);
        return new GenericResponse<Product>(200,productToUpdate);
    }

    @Override
    public GenericResponse<AddToFavoritesResponse> addProductToFavorites(String jwt,AddToFavoritesRequest addToFavoritesRequest) {
        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(jwt));
        user.orElseThrow(RuntimeException::new);
        List<ProductFavorites> productFavorites = user.get().getProductFavorites();
        boolean exists = false;
        for(ProductFavorites favoritedProduct : productFavorites){
            if(favoritedProduct.getProduct().getProductUuid().equals(addToFavoritesRequest.getProductUuid())){
                exists=true;
                break;
            }
        }
        if(!exists){
            productFavorites.add(new ProductFavorites(
                    productRepository.getProductByProductUuid(addToFavoritesRequest.getProductUuid()),
                    user.get()
            ));

            productFavoritesRepository.saveAll(productFavorites);

        }
        return new GenericResponse<AddToFavoritesResponse>(200,
                new AddToFavoritesResponse(addToFavoritesRequest.getProductUuid()));
    }

    @Override
    public GenericResponse<Integer> getFavoritesCount(String jwt) {
        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(jwt));
        user.orElseThrow(RuntimeException::new);
        return new GenericResponse<Integer>(200,user.get().getProductFavorites().size());
    }

    @Override
    public GenericResponse<List<Product>> getFavoritedProducts(String jwt) {
        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(jwt));
        user.orElseThrow(RuntimeException::new);
        List<ProductFavorites> productFavoritesList = productFavoritesRepository.getAllByUser_UserUuid(user.get().getUserUuid());
        List<Product> favoritedProducts = new ArrayList<>();
        for(ProductFavorites productFavorites : productFavoritesList){
            favoritedProducts.add(productFavorites.getProduct());
        }

        return new GenericResponse<List<Product>>(200,favoritedProducts);
    }

    @Override
    public GenericResponse<String> removeFavoritedProduct(String jwt, String productUuid) {
        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(jwt));
        user.orElseThrow(RuntimeException::new);
        Optional<Product> productToRemoveFromFavorites = productRepository.findById(productUuid);
        if(productToRemoveFromFavorites.isPresent()){
            List<ProductFavorites> favoritedProducts = user.get().getProductFavorites();
            for(ProductFavorites favoritedProduct : favoritedProducts){
                if(favoritedProduct.getProduct().getProductUuid().equals(productUuid)){
                    productFavoritesRepository.delete(favoritedProduct);
                }
            }
            return new GenericResponse<String>(200,"success");
        }
        else{
            return new GenericResponse<String>(404,"entity not found");
        }

    }

    @Override
    public GenericResponse<List<Product>> getSellerProducts(String jwt) {
        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(jwt));
        user.orElseThrow(RuntimeException::new);
        List<Product> productsOfUser = user.get().getProductsOfSeller();
        return new GenericResponse<List<Product>>(200,productsOfUser);
    }


    @Override
    public List<ProductCategory> getProductCategories(List<Integer> ids) {
        return productCategoryRepository.findByCategoryIdIn(ids);
    }

    @Override
    public GenericResponse<Product> getProductByUUID(String uuid) {
        Product product = productRepository.getProductByProductUuid(uuid);
        return new GenericResponse<Product>(200,product);
    }

}
