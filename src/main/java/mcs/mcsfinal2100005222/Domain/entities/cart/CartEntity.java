package mcs.mcsfinal2100005222.Domain.entities.cart;


import lombok.*;
import mcs.mcsfinal2100005222.Domain.entities.user.UserEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartEntity {


    private String cartUuid;

    private UserEntity cartUser;

    private List<CartItemEntity> cartItems;


}
