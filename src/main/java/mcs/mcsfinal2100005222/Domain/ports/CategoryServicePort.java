package mcs.mcsfinal2100005222.Domain.ports;

import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductCategory;

import java.util.List;

public interface CategoryServicePort {

    List<ProductCategory> getAllCategories();

}
