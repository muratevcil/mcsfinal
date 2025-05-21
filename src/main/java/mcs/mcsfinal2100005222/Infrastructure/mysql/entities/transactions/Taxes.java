package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.transactions;


import jakarta.persistence.*;

@Entity
@Table(name="taxes")
public class Taxes{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    @Column(name="effected_tax_type_id")
    private int taxTypeId;
    @Column(name="effected_user_id")
    private String userId;
    @Column(name="effected_seller_id")
    private String sellerUuid;

}
