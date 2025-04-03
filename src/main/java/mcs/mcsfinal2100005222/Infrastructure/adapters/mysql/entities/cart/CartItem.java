package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;

@Entity
@Data
@Getter
@Setter
@Table(name="cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "cart_item_discounted_product_id", nullable = false)
    private Product cartProduct;
    private double quantity;

    @OneToOne
    @JoinColumn(name = "cart_item_discount_product_uuid")
    @Nullable
    private CartCategoryDiscount cartItemDiscountAffectedByCategory;

    @OneToOne
    @Nullable
    @JoinColumn(name = "cart_item_discount_category_uuid")
    private CartProductDiscount cartItemDiscountAffectedByProduct;



}
