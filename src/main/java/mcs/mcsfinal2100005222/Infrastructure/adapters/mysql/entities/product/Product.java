package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productUuid;
    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<ProductCategory> categories;
    @Column(name="product_name")
    private String productName;
    @Column(name="product_description")
    private String productDescription;
    @Column(name="product_stock_quantity")
    private double productStockQuantity;
    @Column(name="product_seller_user_uuid")
    private String productSellerUserUUID;
    @OneToMany(mappedBy = "product")
    private List<ProductEAV> productEAVList;

    @OneToMany(mappedBy = "product")
    private List<ProductViews> productViews;

    @Column(name = "product_uuid", columnDefinition = "VARCHAR(255)")
    @PrePersist
    public void prePersist() {
        if (productUuid == null) {
            productUuid = UUID.randomUUID().toString();
        }
    }
    public Product(){
        this.productUuid= String.valueOf(UUID.randomUUID());
    }

    public String getUuid() {
        return productUuid;
    }

    public void setUuid(String uuid) {
        this.productUuid = uuid;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(String productUuid) {
        this.productUuid = productUuid;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategory> categories) {
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

    public double getProductStockQuantity() {
        return productStockQuantity;
    }

    public void setProductStockQuantity(double productStockQuantity) {
        this.productStockQuantity = productStockQuantity;
    }

    public String getProductSellerUserUUID() {
        return productSellerUserUUID;
    }

    public void setProductSellerUserUUID(String productSellerUserUUID) {
        this.productSellerUserUUID = productSellerUserUUID;
    }

    public List<ProductEAV> getProductEAVList() {
        return productEAVList;
    }

    public void setProductEAVList(List<ProductEAV> productEAVList) {
        this.productEAVList = productEAVList;
    }
}
