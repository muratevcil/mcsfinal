package mcs.mcsfinal2100005222.Domain.entities.user;


import lombok.*;
import mcs.mcsfinal2100005222.Domain.entities.cart.CartEntity;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductViewsEntity;
import mcs.mcsfinal2100005222.Domain.entities.wallet.WalletEntity;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.Abstracts.Role;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.Abstracts.UserType;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    private String userUuid;

    private String name;

    private String username;

    private String password;

    private boolean accountNonExpired;//implemente ettiğimiz superclass yüzünden bunlar var olmak zorunda

    private boolean isEnabled;

    private boolean accountNonLocked;

    private boolean credentialNonExpired;

    private String phoneNumber;

    private String email;

    private CartEntity cart;

    private WalletEntity wallet;

    private Set<Role> authorities;

    private UserType userType;

    private ProductViewsEntity productViews;


}
