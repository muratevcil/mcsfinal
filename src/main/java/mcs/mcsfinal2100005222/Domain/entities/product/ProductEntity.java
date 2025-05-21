package mcs.mcsfinal2100005222.Domain.entities.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    private String productUuid;

    @JsonProperty("productCategories")
    private List<ProductCategoryEntity> categories;

    private String productName;

    private String productDescription;

    private String productSellerUserUUID;

    private double productStockQuantity;

    private List<ProductEAVEntity> productEAVList;

    private List<String> productMediaURLs;

    private boolean isProductPublished;


}

