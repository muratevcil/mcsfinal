package mcs.mcsfinal2100005222.Domain.entities.product;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductEntity {


    private String productUuid;

    @JsonProperty("productCategories")
    private List<ProductCategoryEntity> categories;


    private String productName;


    private String productDescription;


    private String productSellerUserUUID;


    private double productStockQuantity;

    private List<ProductEAVEntity> productEAVList;

    public String getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(String productUuid) {
        this.productUuid = productUuid;
    }

    public List<ProductCategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategoryEntity> categories) {
        this.categories = categories;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductSellerUserUUID() {
        return productSellerUserUUID;
    }

    public void setProductSellerUserUUID(String productSellerUserUUID) {
        this.productSellerUserUUID = productSellerUserUUID;
    }

    public double getProductStockQuantity() {
        return productStockQuantity;
    }

    public void setProductStockQuantity(double productStockQuantity) {
        this.productStockQuantity = productStockQuantity;
    }

    public List<ProductEAVEntity> getProductEAVList() {
        return productEAVList;
    }

    public void setProductEAVList(List<ProductEAVEntity> productEAVList) {
        this.productEAVList = productEAVList;
    }
}
