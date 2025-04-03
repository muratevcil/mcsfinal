package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="product_eav")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEAV { // EAV stands for entity-attribute-value

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productEavId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="productUuid")
    @JsonIgnore
    private Product product;

    @Column(name="attribute_name")
    private String attributeName;

    @Column(name="attribute_value")
    private String attributeValue;

}
