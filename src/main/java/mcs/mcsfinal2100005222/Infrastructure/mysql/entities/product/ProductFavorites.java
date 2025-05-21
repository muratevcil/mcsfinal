package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.User;

@Entity
@Data
@Table(name="product_favorites")
@AllArgsConstructor
@NoArgsConstructor
public class ProductFavorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_favorites_id")
    private int productFavoritesId;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    public ProductFavorites(Product product, User user){
        this.product = product;
        this.user = user;
    }


}
