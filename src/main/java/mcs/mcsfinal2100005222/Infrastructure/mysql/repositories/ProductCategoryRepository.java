package mcs.mcsfinal2100005222.Infrastructure.mysql.repositories;

import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryIdIn(List<Integer> ids);

}
