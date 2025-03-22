package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart.Cart;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.ProductViews;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.Abstracts.UserType;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.wallet.Wallet;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.userdetails.UserDetails;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.Abstracts.Role;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.Builder;

@Entity
@Getter
@Setter
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
    private UserType userType;

    @OneToMany(mappedBy = "user")
    @Nullable
    private List<ProductViews> productViews;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name="user_cart_uuid")
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name="user_wallet_uuid")
    private Wallet wallet;

    @Version
    private int version;
    public User(String uuid,String name, String username, String password, boolean accountNonExpired, boolean isEnabled, boolean accountNonLocked, boolean credentialNonExpired, Set<Role> authorities, String phoneNumber, String email) {
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
        this.userType = UserType.TYPE_FREE;
    }

    public User() {

    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialNonExpired() {
        return credentialNonExpired;
    }

    public void setCredentialNonExpired(boolean credentialNonExpired) {
        this.credentialNonExpired = credentialNonExpired;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Nullable
    public List<ProductViews> getProductViews() {
        return productViews;
    }

    public void setProductViews(@Nullable List<ProductViews> productViews) {
        this.productViews = productViews;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "User{" +
                "userUuid='" + userUuid + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", isEnabled=" + isEnabled +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialNonExpired=" + credentialNonExpired +
                ", authorities=" + authorities +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                ", productViews=" + productViews +
                ", cart=" + cart +
                ", wallet=" + wallet +
                ", version=" + version +
                '}';
    }
}
