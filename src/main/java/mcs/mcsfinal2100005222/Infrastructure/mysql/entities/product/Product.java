package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.CartItem;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.transactions.Transaction;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Indexed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@Indexed
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="product_uuid")
    private String productUuid;
    @ManyToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @Column(name="categories")
    @JsonIgnore
    private List<ProductCategory> productCategories;

    @ElementCollection(targetClass = String.class)
    private List<String> productMediaURLs;

    @Column(name="product_price")
    private double productPrice;

    @Column(name="product_name")
    private String productName;
    @Column(name="product_description")
    private String productDescription;
    @Column(name="product_stock_quantity")
    private double productStockQuantity;
    @Column(name="product_seller_user_uuid")
    private String productSellerUserUUID;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductEAV> productEAVList;

    @OneToMany(mappedBy = "cartProduct")
    @JsonIgnore
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
    @JsonIgnore
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductFavorites> productFavorites;

    @Column(name="product_review_score")
    private Double productReviewScore;

    @ManyToOne
    @Nullable
    @JsonIgnore
    private User user;

}
