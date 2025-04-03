package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories;


import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart.Cart;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername(String userName);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    String findCart_CartUuidByUserUuid(String userUuid);

}
