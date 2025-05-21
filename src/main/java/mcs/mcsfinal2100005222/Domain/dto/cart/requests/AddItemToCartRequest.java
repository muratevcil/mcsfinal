package mcs.mcsfinal2100005222.Domain.dto.cart.requests;

import jakarta.annotation.Nullable;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddItemToCartRequest {

    @Nullable
    private String productUuid;

    @NonNull
    private Double productQuantity;

    @Nullable
    private List<String> affectedProductDiscountUuidList;

}
