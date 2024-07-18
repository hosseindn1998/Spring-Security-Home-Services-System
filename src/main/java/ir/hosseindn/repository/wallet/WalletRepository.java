package ir.hosseindn.repository.wallet;

import ir.hosseindn.model.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Modifying
    @Query("UPDATE Wallet w SET w.amount=w.amount - :amount WHERE w.id=:customerId")
    void withdraw(@Param("customerId")Long customerId,@Param("amount")Long amount);
    @Modifying
    @Query("UPDATE Wallet w SET w.amount=w.amount + :amount WHERE w.id=:technicianId")
    void deposit(@Param("technicianId")Long technicianId,@Param("amount")Long amount);
}
