package mcs.mcsfinal2100005222.Infrastructure.mysql.repositories;

import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.CartProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductDiscountRepository extends JpaRepository<CartProductDiscount,String> {
}
