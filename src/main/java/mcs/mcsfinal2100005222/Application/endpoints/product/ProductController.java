package mcs.mcsfinal2100005222.Application.endpoints.product;


import mcs.mcsfinal2100005222.Domain.dto.product.requests.CreateNewProductRequest;
import mcs.mcsfinal2100005222.Domain.dto.product.requests.EditProductRequest;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Domain.ports.ProductServicePort;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;
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
    public GenericResponse<Product> addNewProduct(@RequestBody CreateNewProductRequest createNewProductRequest){
        return productManager.addNewProduct(createNewProductRequest);
    }

    @GetMapping("/getProductByUUID/{uuid}")
    public GenericResponse<Product> getProductByUUID(@PathVariable String uuid){
        return productManager.getProductByUUID(uuid);
    }

    @PatchMapping("/editProduct")
    public GenericResponse<Product> editProduct(@RequestBody EditProductRequest editProductRequest){
        return productManager.editProduct(editProductRequest);
    }


}
