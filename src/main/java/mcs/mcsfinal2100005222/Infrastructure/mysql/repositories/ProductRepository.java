package mcs.mcsfinal2100005222.Infrastructure.mysql.repositories;

import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Product getProductByProductUuid(String uuid);

    List<Product> getProductByProductSellerUserUUID(String uuid);

}
