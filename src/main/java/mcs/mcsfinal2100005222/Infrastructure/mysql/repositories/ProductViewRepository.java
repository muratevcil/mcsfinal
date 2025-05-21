package mcs.mcsfinal2100005222.Infrastructure.mysql.repositories;


import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductViews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductViewRepository extends JpaRepository<ProductViews,Integer> {
}
