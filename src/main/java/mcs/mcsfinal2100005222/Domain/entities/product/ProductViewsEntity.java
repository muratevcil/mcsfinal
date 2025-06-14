package mcs.mcsfinal2100005222.Domain.entities.product;


import lombok.*;
import mcs.mcsfinal2100005222.Domain.entities.user.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductViewsEntity {

    private ProductEntity product;

    private UserEntity user;

}
