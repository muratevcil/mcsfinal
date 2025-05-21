package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.transactions;


import jakarta.persistence.*;

@Entity
@Table(name="transaction_contact")
public class TransactionContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "transaction_uuid")
    private String transactionUuid;
    @Column(name="email")
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;

}
