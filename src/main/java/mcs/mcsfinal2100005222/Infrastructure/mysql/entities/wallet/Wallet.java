package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.wallet;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.transactions.Transaction;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.User;

import java.util.List;

@Entity
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String walletUuid;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "wallet_user_uuid", referencedColumnName = "userUuid", unique = true)
    private User user;
    @Column(name="wallet_balance")
    private double walletBalance;

    @OneToMany(mappedBy = "wallet")
    private List<Transaction> walletTransactions;
    public Wallet(User user){
        this.user = user;
    }

}
