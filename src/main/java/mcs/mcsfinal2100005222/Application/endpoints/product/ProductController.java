package mcs.mcsfinal2100005222.Application.endpoints.product;


import mcs.mcsfinal2100005222.Domain.dto.product.CreateNewProductRequest;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Domain.ports.ProductServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServicePort productManager;

    @GetMapping( "/getAllProducts")
    public GenericResponse<List<ProductEntity>> getProductsByCategoryId(){
        System.out.println("çalış");
        return productManager.getAllProducts();
    }

    @PostMapping("/addNewProduct")
    public GenericResponse<ProductEntity> addNewProduct(@RequestBody CreateNewProductRequest createNewProductRequest){
        return productManager.addNewProduct(createNewProductRequest);
    }


}
