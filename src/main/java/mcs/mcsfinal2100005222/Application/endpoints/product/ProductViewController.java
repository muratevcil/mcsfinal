package mcs.mcsfinal2100005222.Application.endpoints.product;


import mcs.mcsfinal2100005222.Domain.dto.productview.responses.ProductViewAddResponse;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/productView")
@RestController
public class ProductViewController {

    @PostMapping("/setProductViewed")
    private ProductViewAddResponse setProductViewedResponse(@RequestBody ProductViewAddResponse productViewAddResponse){

        return null;

    }

    @GetMapping("/getProductViewByUserId")
    private List<Product> getProductByUser(){

        return null;

    }

}
