package mcs.mcsfinal2100005222.Domain.ports;

import mcs.mcsfinal2100005222.Domain.dto.product.requests.CreateNewProductRequest;
import mcs.mcsfinal2100005222.Domain.dto.product.requests.EditProductRequest;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.ProductCategory;

import java.util.List;

public interface ProductServicePort {

    GenericResponse<List<ProductEntity>> getAllProducts();

    GenericResponse<Product> addNewProduct(CreateNewProductRequest createNewProductRequest);

    List<ProductCategory> getProductCategories(List<Integer> ids);

    GenericResponse<Product> getProductByUUID(String uuid);

    GenericResponse<Product> editProduct(EditProductRequest editProductRequest);

}
