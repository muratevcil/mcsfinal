package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;

@Entity
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

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(Product cartProduct) {
        this.cartProduct = cartProduct;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Nullable
    public CartCategoryDiscount getCartItemDiscountAffectedByCategory() {
        return cartItemDiscountAffectedByCategory;
    }

    public void setCartItemDiscountAffectedByCategory(@Nullable CartCategoryDiscount cartItemDiscountAffectedByCategory) {
        this.cartItemDiscountAffectedByCategory = cartItemDiscountAffectedByCategory;
    }

    @Nullable
    public CartProductDiscount getCartItemDiscountAffectedByProduct() {
        return cartItemDiscountAffectedByProduct;
    }

    public void setCartItemDiscountAffectedByProduct(@Nullable CartProductDiscount cartItemDiscountAffectedByProduct) {
        this.cartItemDiscountAffectedByProduct = cartItemDiscountAffectedByProduct;
    }



}
