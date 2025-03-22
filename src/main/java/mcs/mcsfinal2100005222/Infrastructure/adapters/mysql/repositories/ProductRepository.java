package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories;

import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.ProductCategory;
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

    Product getProductByProductUuid(String uuid);

    List<Product> getProductByProductSellerUserUUID(String uuid);

}
