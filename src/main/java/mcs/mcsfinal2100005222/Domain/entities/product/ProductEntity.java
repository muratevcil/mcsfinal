package mcs.mcsfinal2100005222.Domain.entities.product;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.ProductCategoryRepository;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;

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

