package mcs.mcsfinal2100005222.Domain.entities.cart;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemEntity {

    private Long cartItemId;

    private String productUuid;

    private double productQuantity;


}
