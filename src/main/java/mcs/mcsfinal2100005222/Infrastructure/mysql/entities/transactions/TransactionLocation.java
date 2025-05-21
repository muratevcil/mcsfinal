package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.transactions;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="transaction_location")
public class TransactionLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="transaction_location_uuid")
    private String transactionLocationUuid;
    @Column(name="address_part1")
    private String addressPart1;

    @Column(name="address_part2")
    private String addressPart2;

    @Column(name="address_part3")
    private String addressPart3;

    @Column(name="address_part4")
    private String addressPart4;

    @Column(name="zip_code")
    private String zipCode;

    @OneToMany(mappedBy = "transactionLocation")
    private List<Transaction> transactions;

}
