package mcs.mcsfinal2100005222.Domain.dto.product.requests;


import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EditProductRequest {

    private String jwt;

    private String productUuid;

    @Nullable
    private String productName;

    @Nullable
    private String productDescription;

    private Boolean isProductPublished;

}
