package mcs.mcsfinal2100005222.Application.endpoints.categories;


import mcs.mcsfinal2100005222.Domain.ports.CategoryServicePort;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServicePort categoryServicePort;

    @GetMapping("/getAll")
    private List<ProductCategory> getAllCategories(){
        return categoryServicePort.getAllCategories();
    }

}
