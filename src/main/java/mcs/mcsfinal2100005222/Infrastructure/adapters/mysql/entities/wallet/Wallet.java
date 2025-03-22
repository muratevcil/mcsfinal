package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.wallet;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.transactions.Transaction;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.User;

import java.util.List;
import java.util.UUID;

@Entity
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


    @Version
    private int version;


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Wallet(User user){
        this.user = user;
    }

    public Wallet() {

    }

    public String getWalletUuid() {
        return walletUuid;
    }

    public void setWalletUuid(String walletUuid) {
        this.walletUuid = walletUuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public List<Transaction> getWalletTransactions() {
        return walletTransactions;
    }

    public void setWalletTransactions(List<Transaction> walletTransactions) {
        this.walletTransactions = walletTransactions;
    }
}
