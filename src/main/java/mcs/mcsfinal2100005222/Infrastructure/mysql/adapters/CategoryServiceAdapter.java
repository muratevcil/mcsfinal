package mcs.mcsfinal2100005222.Infrastructure.mysql.adapters;


import mcs.mcsfinal2100005222.Domain.ports.CategoryServicePort;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductCategory;
import mcs.mcsfinal2100005222.Infrastructure.mysql.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceAdapter implements CategoryServicePort {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }
}
