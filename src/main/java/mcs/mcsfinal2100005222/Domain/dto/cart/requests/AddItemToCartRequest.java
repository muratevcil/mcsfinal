package mcs.mcsfinal2100005222.Domain.dto.cart.requests;

import jakarta.annotation.Nullable;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddItemToCartRequest {

    private String jwt;

    @Nullable
    private String productUuid;

    private double productQuantity;

}
