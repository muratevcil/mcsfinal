package mcs.mcsfinal2100005222.Infrastructure.adapters;


import mcs.mcsfinal2100005222.Domain.dto.product.CreateNewProductRequest;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Domain.ports.ProductServicePort;
import mcs.mcsfinal2100005222.Domain.security.concretes.JwtManager;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.User;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.ProductCategoryRepository;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.ProductRepository;
import mcs.mcsfinal2100005222.Domain.utils.modelmapper.ModelMapperService;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public GenericResponse<ProductEntity> addNewProduct(CreateNewProductRequest createNewProductRequest){

        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(createNewProductRequest.getJwt()));
        user.orElseThrow(RuntimeException::new);
        ProductEntity productEntity = modelMapper.forRequest().map(createNewProductRequest, ProductEntity.class);
        productEntity.setProductUuid(UUID.randomUUID().toString());
        productEntity.setProductSellerUserUUID(user.get().getUserUuid());
        Product productDbEntity = modelMapper.forResponse().map(productEntity,Product.class);
        System.out.println(productDbEntity);
        return new GenericResponse<ProductEntity>(200,productEntity);

    }

}
