package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories;

import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.queries.GetAllProductsQueryDTO;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.queries.ProductQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findAll();

    Product findByUuid(String uuid);

    List<Product> findByProductSellerUserUUID(String uuid);

    @Query(nativeQuery = true, value = ProductQueries.getProductByIdQuery)
    public Product getProductByUUID(@Param("uuid") String uuid);

    @Query(nativeQuery = true, value = ProductQueries.getAllProducts)
    public List<GetAllProductsQueryDTO> getAllProducts();

    @Query(nativeQuery = true, value = ProductQueries.getAllProductsForCategory)
    public List<Product> getAllProductsForCategory();

}
