package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.ProductCategory;

import java.util.List;

@Entity
@Table(name="cart_category_discount")
public class CartCategoryDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="cart_category_discount_uuid")
    private String cartDiscountUuid;

    @ManyToOne
    private ProductCategory cartDiscountCategories;

    @Column(name="cart_discount_amount")
    private double cartDiscountAmount;

    public String getCartDiscountUuid() {
        return cartDiscountUuid;
    }

    public void setCartDiscountUuid(String cartDiscountUuid) {
        this.cartDiscountUuid = cartDiscountUuid;
    }

    public ProductCategory getCartDiscountCategories() {
        return cartDiscountCategories;
    }

    public void setCartDiscountCategories(ProductCategory cartDiscountCategories) {
        this.cartDiscountCategories = cartDiscountCategories;
    }

    public double getCartDiscountAmount() {
        return cartDiscountAmount;
    }

    public void setCartDiscountAmount(double cartDiscountAmount) {
        this.cartDiscountAmount = cartDiscountAmount;
    }
}
