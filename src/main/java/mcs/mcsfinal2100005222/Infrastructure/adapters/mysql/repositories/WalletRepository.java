package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.repositories;

import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,String> {
}
