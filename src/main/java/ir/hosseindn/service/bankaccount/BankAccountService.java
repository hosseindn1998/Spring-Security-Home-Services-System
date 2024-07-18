package ir.hosseindn.service.bankaccount;

import ir.hosseindn.model.BankAccount;
import ir.hosseindn.repository.bankaccount.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public Boolean isValidCardInfo(String cardNumber, String cvv, String mm, String yy, String password) {
        return bankAccountRepository.existsByCardNumberAndCvvAndMmAndYyAndPassword(cardNumber, cvv, mm, yy, password);
    }

    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public void withdraw(String cardNumber, Long amount) {
        bankAccountRepository.withdraw(cardNumber, amount);
    }

}
