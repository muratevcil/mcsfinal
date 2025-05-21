package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.CartCategoryDiscount;


import java.util.List;

@Entity
@Data
@Table(name="product_category")
@ToString
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private int categoryId;
    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private List<Product> product;
    @Column(name="category_name")
    private String categoryName;
    @Column(name="category_description")
    private String categoryDescription;
    @Column(name="category_img_url")
    private String categoryImgUrl;
    @OneToMany(mappedBy = "cartDiscountCategories")
    private List<CartCategoryDiscount> cartCategoryDiscounts;

}
