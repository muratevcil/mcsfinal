package mcs.mcsfinal2100005222.Application.endpoints.product;


import mcs.mcsfinal2100005222.Domain.dto.product.requests.CreateNewProductRequest;
import mcs.mcsfinal2100005222.Domain.dto.product.requests.EditProductRequest;
import mcs.mcsfinal2100005222.Domain.dto.product.responses.MainPageProductResponse;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Domain.ports.ProductServicePort;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServicePort productManager;

    @GetMapping( "/getAllProducts")
    public GenericResponse<List<ProductEntity>> getProductsByCategoryId(){
        return productManager.getAllProducts();
    }

    @GetMapping("/getMainPageProducts")
    public GenericResponse<Page<MainPageProductResponse>> getMainPageProducts(@PageableDefault(size = 10) Pageable pageable){
        return productManager.getMainPageProducts(pageable);
    }

    @PostMapping("/addNewProduct")
    public GenericResponse<Product> addNewProduct(@RequestBody CreateNewProductRequest createNewProductRequest){
        return productManager.addNewProduct(createNewProductRequest);
    }

    @GetMapping("/getProductByUUID/{uuid}")
    public GenericResponse<Product> getProductByUUID(@PathVariable String uuid){
        return productManager.getProductByUUID(uuid);
    }

    @PostMapping("/editProduct")
    public GenericResponse<Product> editProduct(@RequestBody EditProductRequest editProductRequest){
        System.out.println("product update tetiklendi");
        return productManager.editProduct(editProductRequest);
    }

    @GetMapping("/getSellerProducts")
    public GenericResponse<List<Product>> getSellerProducts(@RequestHeader("Authorization") String jwt){
        return productManager.getSellerProducts(jwt.substring(7));
    }


}
