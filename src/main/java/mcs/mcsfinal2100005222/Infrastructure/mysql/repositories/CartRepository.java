package mcs.mcsfinal2100005222.Infrastructure.mysql.repositories;


import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {


}
