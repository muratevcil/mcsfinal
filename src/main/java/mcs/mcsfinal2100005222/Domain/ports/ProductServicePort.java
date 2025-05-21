package mcs.mcsfinal2100005222.Domain.ports;

import mcs.mcsfinal2100005222.Domain.dto.favorites.requests.AddToFavoritesRequest;
import mcs.mcsfinal2100005222.Domain.dto.favorites.responses.AddToFavoritesResponse;
import mcs.mcsfinal2100005222.Domain.dto.product.requests.CreateNewProductRequest;
import mcs.mcsfinal2100005222.Domain.dto.product.requests.EditProductRequest;
import mcs.mcsfinal2100005222.Domain.dto.product.responses.MainPageProductResponse;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductServicePort {

    GenericResponse<List<ProductEntity>> getAllProducts();

    GenericResponse<Page<MainPageProductResponse>> getMainPageProducts(Pageable pageable);

    GenericResponse<Product> addNewProduct(CreateNewProductRequest createNewProductRequest);

    List<ProductCategory> getProductCategories(List<Integer> ids);

    GenericResponse<Product> getProductByUUID(String uuid);

    GenericResponse<Product> editProduct(EditProductRequest editProductRequest);

    GenericResponse<AddToFavoritesResponse> addProductToFavorites(String jwt,AddToFavoritesRequest addToFavoritesRequest);

    GenericResponse<Integer> getFavoritesCount(String jwt);

    GenericResponse<List<Product>> getFavoritedProducts(String jwt);

    GenericResponse<String> removeFavoritedProduct(String jwt,String productUuid);

    GenericResponse<List<Product>> getSellerProducts(String jwt);

}
