package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.transactions;


import jakarta.persistence.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.wallet.Wallet;

import java.util.List;

@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="transaction_uuid")
    private String transactionUuid;

    @ManyToMany(mappedBy = "transactions")
    private List<Product> productList;

    @Column(name="quantity")
    private double quantity;

    @Column(name="price_per_unit")
    private double price_per_unit;

    @Column(name="total_price")
    private double total_price;

    @Column(name="tax_id")
    private int taxId;

    @ManyToOne
    @JoinColumn(name="wallet_uuid",referencedColumnName = "walletUuid")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name="transaction_location_uuid")
    private TransactionLocation transactionLocation;

}
