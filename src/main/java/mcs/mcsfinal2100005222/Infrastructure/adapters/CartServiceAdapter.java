package mcs.mcsfinal2100005222.Infrastructure.adapters;

import jakarta.persistence.EntityNotFoundException;
import mcs.mcsfinal2100005222.Domain.dto.cart.requests.AddItemToCartRequest;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.ports.CartServicePort;
import mcs.mcsfinal2100005222.Domain.security.concretes.JwtManager;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart.Cart;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.User;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceAdapter implements CartServicePort {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtManager jwtManager;

    @Override
    public GenericResponse<Cart> addItemToCart(AddItemToCartRequest addItemToCartRequest) {

        //user repository fonksiyonundan çıktı null olarak dönüyor. bunu fixle, 03.04.2025 15:41

        String cartId = userRepository.findCart_CartUuidByUserUuid(jwtManager.extractUser(addItemToCartRequest.getJwt()));
        System.out.println(cartId);


        return null;
    }

}
