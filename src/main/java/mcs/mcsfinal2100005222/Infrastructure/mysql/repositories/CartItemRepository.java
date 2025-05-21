package mcs.mcsfinal2100005222.Infrastructure.mysql.repositories;

import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    CartItem findByCartProduct_ProductUuid(String uuid);
    List<CartItem> findAllByCart_CartUuid(String cartUuid);
    void removeByCartProduct_ProductUuid(String uuid);
    void deleteCartItemByCartProduct_ProductUuid(String uuid);

}
