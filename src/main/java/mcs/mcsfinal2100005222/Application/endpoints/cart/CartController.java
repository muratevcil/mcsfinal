package mcs.mcsfinal2100005222.Application.endpoints.cart;


import mcs.mcsfinal2100005222.Domain.dto.cart.requests.AddItemToCartRequest;
import mcs.mcsfinal2100005222.Domain.ports.CartServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServicePort cartServicePort;

    @PostMapping("/addItemToCart")
    public void addItemToCart(@RequestBody AddItemToCartRequest addItemToCartRequest){
        cartServicePort.addItemToCart(addItemToCartRequest);
    }


}
