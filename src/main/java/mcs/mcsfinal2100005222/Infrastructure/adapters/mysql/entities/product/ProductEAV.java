package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product;


import jakarta.persistence.*;

@Entity
@Table(name="product_eav")
public class ProductEAV { // EAV stands for entity-attribute-value

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productEavId;

    @ManyToOne
    @JoinColumn(name="productUuid")
    private Product product;

    @Column(name="attribute_name")
    private String attributeName;

    @Column(name="attribute_value")
    private String attributeValue;

    public int getProductEavId() {
        return productEavId;
    }

    public void setProductEavId(int productEavId) {
        this.productEavId = productEavId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
