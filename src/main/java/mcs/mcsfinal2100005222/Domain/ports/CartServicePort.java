package mcs.mcsfinal2100005222.Domain.ports;

import mcs.mcsfinal2100005222.Domain.dto.cart.requests.AddItemToCartRequest;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart.Cart;

public interface CartServicePort {

    GenericResponse<Cart> addItemToCart(AddItemToCartRequest addItemToCartRequest);

}
