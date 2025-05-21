package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.Product;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnore
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "cart_item_product", nullable = false)
    private Product cartProduct;
    private double quantity =0;

    @Column(name="unit_price")
    private double unitPrice;

    @OneToOne
    @JoinColumn(name = "cart_item_discount_product_uuid",nullable = true)
    private CartCategoryDiscount cartItemDiscountAffectedByCategory;

    @OneToOne
    @JoinColumn(name = "cart_item_discount_category_uuid",nullable = true)
    private CartProductDiscount cartItemDiscountAffectedByProduct;

    public CartItem(Cart cart, Product cartProduct, double quantity){
        this.cart = cart;
        this.cartProduct = cartProduct;
        this.quantity = quantity;
        this.unitPrice = cartProduct.getProductPrice();
    }

}
