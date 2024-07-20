package ir.hosseindn.repository.paymenttransaction;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PaymentTransactionRepository extends JpaRepository<ir.hosseindn.model.PaymentTransaction, Long> {
    @Modifying
    @Query("UPDATE PaymentTransaction p SET p.captchaAnswer=:newAnswer where p.id=:id")
    void updateCaptchaAnswer(@Param("id") Long id, @Param("newAnswer") String newAnswer);
}
