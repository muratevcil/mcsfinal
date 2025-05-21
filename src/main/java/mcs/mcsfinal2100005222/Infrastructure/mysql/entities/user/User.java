package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.Cart;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductFavorites;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.ProductViews;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.Abstracts.UserType;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.wallet.Wallet;
import org.springframework.security.core.userdetails.UserDetails;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.Abstracts.Role;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userUuid;

    @Column(name="name")
    private String name;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="account_non_expired")
    private boolean accountNonExpired;//implemente ettiğimiz superclass yüzünden bunlar var olmak zorunda
    @Column(name="is_enabled")
    private boolean isEnabled;
    @Column(name="account_non_locked")
    private boolean accountNonLocked;
    @Column(name="credential_non_expired")
    private boolean credentialNonExpired;


    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @JoinTable(name="authorities",joinColumns=@JoinColumn(name="user_id"))
    @Column(name="role",nullable=false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @Column(name="user_type")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private UserType userType;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @Nullable
    private List<ProductViews> productViews;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    @JoinColumn(name="user_cart_uuid")
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    @JoinColumn(name="user_wallet_uuid")
    private Wallet wallet;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ProductFavorites> productFavorites;

    @OneToMany(mappedBy = "user")
    @Nullable
    @JsonIgnore
    private List<Product> productsOfSeller;


    public User(String uuid,String name, String username, String password, boolean accountNonExpired, boolean isEnabled, boolean accountNonLocked, boolean credentialNonExpired, Set<Role> authorities, String phoneNumber, String email,List<Product> productList) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.isEnabled = isEnabled;
        this.accountNonLocked = accountNonLocked;
        this.credentialNonExpired = credentialNonExpired;
        this.authorities = authorities;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userType = UserType.TYPE_CONSUMER;
        this.productsOfSeller = productList;
    }

    public User() {

    }

}
