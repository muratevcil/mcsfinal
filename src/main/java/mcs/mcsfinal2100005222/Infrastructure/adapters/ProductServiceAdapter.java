package mcs.mcsfinal2100005222.Infrastructure.adapters;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import mcs.mcsfinal2100005222.Domain.dto.product.requests.CreateNewProductRequest;
import mcs.mcsfinal2100005222.Domain.dto.product.requests.EditProductRequest;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductCategoryEntity;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Domain.ports.ProductServicePort;
import mcs.mcsfinal2100005222.Domain.security.concretes.JwtManager;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.ProductCategory;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.ProductEAV;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.User;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.ProductCategoryRepository;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.ProductEAVRepository;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.ProductRepository;
import mcs.mcsfinal2100005222.Domain.utils.modelmapper.ModelMapperService;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        ProductEntity productEntity = modelMapper.forRequest().map(editProductRequest,ProductEntity.class);
        Product productToUpdate = productRepository.getProductByProductUuid(editProductRequest.getProductUuid());
        modelMapper.forResponse().map(productEntity,productToUpdate);
        System.out.println(productToUpdate.toString());
        productRepository.save(productToUpdate);
        return new GenericResponse<Product>(200,productToUpdate);
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
