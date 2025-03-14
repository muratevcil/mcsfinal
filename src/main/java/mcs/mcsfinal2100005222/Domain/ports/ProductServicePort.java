package mcs.mcsfinal2100005222.Domain.ports;

import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductServicePort {

    List<ProductEntity> getAllProducts();

}
