package mcs.mcsfinal2100005222.Domain.ports;

import mcs.mcsfinal2100005222.Domain.dto.cart.requests.*;
import mcs.mcsfinal2100005222.Domain.dto.cart.responses.IncrementOrDecrementItemResponse;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.cart.CartItemEntity;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.Cart;

import java.util.List;

public interface CartServicePort {

    GenericResponse<Cart> addItemToCart(String jwt, AddItemToCartRequest addItemToCartRequest);

    GenericResponse<List<CartItemEntity>> getCartContent(String jwt);

    GenericResponse<IncrementOrDecrementItemResponse> incrementOrDecrementItemCount(IncrementOrDecrementCartItemRequest incrementOrDecrementCartItemRequest);

    GenericResponse<Integer> getCartItemCount(String jwt);

    GenericResponse<Double> getCartTotalprice(String jwt);

    GenericResponse<Object> removeCartItem(String jwt,String productUuid);

}
