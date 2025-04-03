package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.cart.CartItem;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.transactions.Transaction;
import org.hibernate.annotations.CreationTimestamp;
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
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="product_uuid")
    private String productUuid;

    @ManyToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @Column(name="categories")
    private List<ProductCategory> productCategories;

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

    @CreationTimestamp
    @Nullable
    @Column(name="created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Nullable
    @Column(name="updated_date")
    private Date updatedDate;

    @ManyToMany
    @Nullable
    @JoinTable(
            name = "product_transactions",
            joinColumns = @JoinColumn(name = "product_uuid",referencedColumnName = "product_uuid"),
            inverseJoinColumns = @JoinColumn(name="transaction_uuid",referencedColumnName = "transaction_uuid")
    )
    private List<Transaction> transactions;

}

