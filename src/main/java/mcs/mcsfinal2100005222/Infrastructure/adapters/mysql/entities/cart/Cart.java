package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.User;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cartUuid;
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "cart_user_uuid", referencedColumnName = "userUuid", unique = true)
    private User cartUser;
    @OneToMany
    private List<CartItem> cartItems;
    @Column(name="cartTotalPrice")
    private double cartTotalPrice;

    @Version
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Cart(User user){
        this.cartUser = user;
    }

    public Cart() {

    }



    public String getCartUuid() {
        return cartUuid;
    }

    public void setCartUuid(String cartUuid) {
        this.cartUuid = cartUuid;
    }

    public User getCartUser() {
        return cartUser;
    }

    public void setCartUser(User cartUser) {
        this.cartUser = cartUser;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(double cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }
}
