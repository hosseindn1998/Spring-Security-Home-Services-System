package ir.hosseindn.repository.bankaccount;

import ir.hosseindn.model.BankAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

    Boolean existsByCardNumberAndCvvAndMmAndYyAndPassword(String cardNumber, String cvv, String mm, String yy, String password);
    @Modifying
    @Transactional
    @Query("update BankAccount b SET b.amount=(b.amount-:amount) where b.cardNumber=:cardNumber")
    void withdraw(@Param("cardNumber") String cardNumber,@Param("amount") Long amount);
    Optional<BankAccount>findByCardNumber(String cardNumber);
}
