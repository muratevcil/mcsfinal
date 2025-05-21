package mcs.mcsfinal2100005222.Infrastructure.mysql.repositories;

import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.CartCategoryDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartCategoryDiscountRepository extends JpaRepository<CartCategoryDiscount,String> {

}
