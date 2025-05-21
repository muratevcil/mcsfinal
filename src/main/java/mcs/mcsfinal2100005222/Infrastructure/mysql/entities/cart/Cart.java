package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.User;

import java.util.List;

@Entity
@Getter
@Setter
@Data
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

    public Cart(User user){
        this.cartUser = user;
    }

    public Cart() {

    }

}
