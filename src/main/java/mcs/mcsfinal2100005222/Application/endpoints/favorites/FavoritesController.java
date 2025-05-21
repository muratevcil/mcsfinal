package mcs.mcsfinal2100005222.Application.endpoints.favorites;


import mcs.mcsfinal2100005222.Domain.dto.favorites.requests.AddToFavoritesRequest;
import mcs.mcsfinal2100005222.Domain.dto.favorites.responses.AddToFavoritesResponse;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.ports.ProductServicePort;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private ProductServicePort productServicePort;

    @PostMapping("/addToFavorites")
    private GenericResponse<AddToFavoritesResponse> addProductToFavorites(@RequestHeader("Authorization") String jwt,@RequestBody AddToFavoritesRequest addToFavoritesRequest){
        return productServicePort.addProductToFavorites(jwt.substring(7),addToFavoritesRequest);
    }

    @GetMapping("/getFavoritesCount")
    private GenericResponse<Integer> getFavoritesCount(@RequestHeader("Authorization") String jwt){
        return productServicePort.getFavoritesCount(jwt.substring(7));
    }

    @GetMapping("/getFavoritedProducts")
    private GenericResponse<List<Product>> getFavoritedProducts(@RequestHeader("Authorization") String jwt){
        return productServicePort.getFavoritedProducts(jwt.substring(7));
    }

    @DeleteMapping("/removeFavoritedProduct/{productUuid}")
    private GenericResponse<String> removeFavoritedProduct(@RequestHeader("Authorization") String jwt,@PathVariable String productUuid){
        return productServicePort.removeFavoritedProduct(jwt.substring(7),productUuid);
    }

}
