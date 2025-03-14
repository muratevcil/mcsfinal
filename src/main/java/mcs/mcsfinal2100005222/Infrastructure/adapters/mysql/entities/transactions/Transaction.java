package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.transactions;


import jakarta.persistence.*;

@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="uuid")
    private String uuid;

    @Column(name="product_uuid")
    private String productUuid;

    @Column(name="user_uuid")
    private String userUuid;

    @Column(name="quantity")
    private double quantity;

    @Column(name="price_per_unit")
    private double price_per_unit;

    @Column(name="total_price")
    private double total_price;

    @Column(name="tax_id")
    private int taxId;

}
