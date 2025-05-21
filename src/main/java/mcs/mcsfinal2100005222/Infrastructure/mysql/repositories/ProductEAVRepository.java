package mcs.mcsfinal2100005222.Infrastructure.mysql.repositories;

import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductEAV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductEAVRepository extends JpaRepository<ProductEAV,Integer> {

    

}
