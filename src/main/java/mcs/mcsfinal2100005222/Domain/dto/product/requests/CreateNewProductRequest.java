package mcs.mcsfinal2100005222.Domain.dto.product.requests;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEAVEntity;
import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;

import java.util.List;



@Data
@Getter
@Setter
public class CreateNewProductRequest {

    private String jwt;

    private String productName;

    private String productDescription;

    private Double productStockQuantity;

    private List<ProductEAVEntity> productEAVList;

    private boolean isProductPublished;

    private List<Integer> productCategories;

    private List<String> productMediaURLs;

}
