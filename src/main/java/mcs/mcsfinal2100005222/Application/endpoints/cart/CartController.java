package mcs.mcsfinal2100005222.Application.endpoints.cart;


import mcs.mcsfinal2100005222.Domain.dto.cart.requests.*;
import mcs.mcsfinal2100005222.Domain.dto.cart.responses.IncrementOrDecrementItemResponse;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.cart.CartItemEntity;
import mcs.mcsfinal2100005222.Domain.ports.CartServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServicePort cartServicePort;

    @PostMapping("/addItemToCart")
    public void addItemToCart(@RequestHeader("Authorization") String jwt, @RequestBody AddItemToCartRequest addItemToCartRequest){
        cartServicePort.addItemToCart(jwt.substring(7), addItemToCartRequest);
    }

    @GetMapping("/getCartContent")
    public GenericResponse<List<CartItemEntity>> getCartContent(@RequestHeader("Authorization") String jwt){
        return cartServicePort.getCartContent(jwt.substring(7));
    }

    @PostMapping("/incrementOrDecrementItemCount")
    public GenericResponse<IncrementOrDecrementItemResponse> incrementOrDecrementItemCount(@RequestBody IncrementOrDecrementCartItemRequest incrementOrDecrementCartItemRequest){
        return cartServicePort.incrementOrDecrementItemCount(incrementOrDecrementCartItemRequest);
    }

    @GetMapping("/getCartItemCount")
    public GenericResponse<Integer> getCartItemCount(@RequestHeader("Authorization") String jwt){
        System.out.println("Acquired jwt:" +jwt.substring(7));
        return cartServicePort.getCartItemCount(jwt.substring(7));
    }

    @GetMapping("/getCartTotalPrice")
    public GenericResponse<Double> getCartTotalPrice(@RequestHeader("Authorization") String jwt){
        return cartServicePort.getCartTotalprice(jwt.substring(7));
    }

    @DeleteMapping("/removeCartItem/{productUuid}")
    public GenericResponse<Object> removeCartItem(@RequestHeader("Authorization") String jwt,@PathVariable("productUuid") String productUuid){
        return cartServicePort.removeCartItem(jwt.substring(7),productUuid);
    }


}
