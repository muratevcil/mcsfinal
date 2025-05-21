package mcs.mcsfinal2100005222.Domain.dto.product.requests;


import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class EditProductRequest {

    private String productUuid;

    @Nullable
    private String productName;

    @Nullable
    private String productDescription;

    @Nullable
    private Double productPrice;

    @Nullable
    private Double productStockQuantity;

    @Nullable
    private List<String> productMediaURLs;

    @Nullable
    private Boolean isProductPublished;

}
