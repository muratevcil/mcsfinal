package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart.CartCategoryDiscount;


import java.util.List;

@Entity
@Table(name="product_category")
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
    private List<Product> product;
    @Column(name="category_name")
    private String categoryName;
    @Column(name="category_description")
    private String categoryDescription;

    @OneToMany(mappedBy = "cartDiscountCategories")
    private List<CartCategoryDiscount> cartCategoryDiscounts;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<CartCategoryDiscount> getCartCategoryDiscounts() {
        return cartCategoryDiscounts;
    }

    public void setCartCategoryDiscounts(List<CartCategoryDiscount> cartCategoryDiscounts) {
        this.cartCategoryDiscounts = cartCategoryDiscounts;
    }
}
