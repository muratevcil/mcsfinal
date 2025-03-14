package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class GetAllProductsQueryDTO {
    public GetAllProductsQueryDTO(String productUuid, int productCategoryId, String productDescription,
                                  String productName, String productSellerUserUuid, double productStockQuantity,
                                  int categoryId, String categoryDescription, String categoryName) {
        this.productUuid = productUuid;
        this.productCategoryId = productCategoryId;
        this.productDescription = productDescription;
        this.productName = productName;
        this.productSellerUserUuid = productSellerUserUuid;
        this.productStockQuantity = productStockQuantity;
        this.categoryId = categoryId;
        this.categoryDescription = categoryDescription;
        this.categoryName = categoryName;
    }

    private String productUuid;
    private int productCategoryId;
    private String productDescription;
    private String productName;
    private String productSellerUserUuid;
    private double productStockQuantity;
    private int categoryId;
    private String categoryDescription;
    private String categoryName;

    public String getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(String productUuid) {
        this.productUuid = productUuid;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSellerUserUuid() {
        return productSellerUserUuid;
    }

    public void setProductSellerUserUuid(String productSellerUserUuid) {
        this.productSellerUserUuid = productSellerUserUuid;
    }

    public double getProductStockQuantity() {
        return productStockQuantity;
    }

    public void setProductStockQuantity(double productStockQuantity) {
        this.productStockQuantity = productStockQuantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
