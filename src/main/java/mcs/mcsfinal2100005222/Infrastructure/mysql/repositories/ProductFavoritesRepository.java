package mcs.mcsfinal2100005222.Infrastructure.mysql.repositories;

import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductFavoritesRepository extends JpaRepository<ProductFavorites,Integer> {

    List<ProductFavorites> getAllByUser_UserUuid(String userUuid);

}
