package mcs.mcsfinal2100005222.Application.endpoints;

import lombok.AllArgsConstructor;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Domain.ports.ProductServicePort;
import mcs.mcsfinal2100005222.Infrastructure.mysql.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class Test {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServicePort productManager;

    @GetMapping("/getProductByUUID/{uuid}")
    public String getProductByUUID(@PathVariable String uuid){
        return null;
    }

    @GetMapping( "/getAllProducts")
    public GenericResponse<List<ProductEntity>> getProductsByCategoryId(){
        System.out.println("çalış");
        return productManager.getAllProducts();
    }

}
