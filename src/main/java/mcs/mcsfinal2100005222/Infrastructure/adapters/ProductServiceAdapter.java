package mcs.mcsfinal2100005222.Infrastructure.adapters;


import mcs.mcsfinal2100005222.Domain.entities.product.ProductCategoryEntity;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Domain.ports.ProductServicePort;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.queries.GetAllProductsQueryDTO;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.ProductRepository;
import mcs.mcsfinal2100005222.Infrastructure.adapters.utils.modelmapper.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceAdapter implements ProductServicePort {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapperService modelMapper;

    @Override
    public List<ProductEntity> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductEntity> productListWillBeReturned = productList.stream()
                .map(product ->
                    modelMapper.forResponse().map(product, ProductEntity.class)
                )
                .toList();
        return productListWillBeReturned;
    }
}
