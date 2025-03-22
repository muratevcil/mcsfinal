package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart.CartItem;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.transactions.Transaction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Indexed;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="product")
@Indexed
@Data
@Getter
@Setter
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="product_uuid")
    private String productUuid;
    @ManyToMany(mappedBy = "product")
    @Column(name="categories")
    private List<ProductCategory> productCategories;

    @Column(columnDefinition = "json")
    @ElementCollection(targetClass = String.class)
    private List<String> productMediaURLs;
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

    @OneToMany(mappedBy = "cartProduct")
    @Nullable
    private List<CartItem> cartItem;


    @Column(name="isProductPublished")
    private boolean isProductPublished;

    @CreatedDate
    @Column(name="created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name="updated_date")
    private Date updatedDate;

    @ManyToMany
    @JoinTable(
            name = "product_transactions",
            joinColumns = @JoinColumn(name = "product_uuid",referencedColumnName = "product_uuid"),
            inverseJoinColumns = @JoinColumn(name="transaction_uuid",referencedColumnName = "transaction_uuid")
    )
    private List<Transaction> transactions;

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

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }


    public boolean isProductPublished() {
        return isProductPublished;
    }

    public void setProductPublished(boolean productPublished) {
        isProductPublished = productPublished;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<String> getProductMediaURLs() {
        return productMediaURLs;
    }

    public void setProductMediaURLs(List<String> productMediaURLs) {
        this.productMediaURLs = productMediaURLs;
    }

}

