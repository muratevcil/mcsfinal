package mcs.mcsfinal2100005222.Application.endpoints;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Domain.ports.ProductServicePort;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.ProductRepository;
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
        return productRepository.getProductByUUID(uuid).getUuid().toString();
    }

    @GetMapping("/getAllProducts")
    public List<ProductEntity> getProductsByCategoryId(){
        return productManager.getAllProducts();
    }

}
