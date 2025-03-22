package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart;


import jakarta.persistence.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;

@Entity
@Table(name="cart_product_discount")
public class CartProductDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="cart_product_discount_uuid")
    private String cartProductDiscountUuid;

    @OneToOne
    private Product product;

    @Column(name="cart_product_discount_amount")
    private double discountAmount;

    public String getCartProductDiscountUuid() {
        return cartProductDiscountUuid;
    }

    public void setCartProductDiscountUuid(String cartProductDiscountUuid) {
        this.cartProductDiscountUuid = cartProductDiscountUuid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
