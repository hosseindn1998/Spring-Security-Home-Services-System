package ir.hosseindn.service.bankaccount;

import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.BankAccount;
import ir.hosseindn.repository.bankaccount.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccount findIfValidAccountInformation(String cardNumber, String cvv, String mm, String yy, String password) {
        return bankAccountRepository.findByCardNumberAndCvvAndMmAndYyAndPassword(cardNumber, cvv, mm, yy, password).orElseThrow(
                () -> new NotValidInformation("Card information Not valid")
        );
    }
    public BankAccount save(BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }
    public BankAccount findByCardNumber(String cardNumber){
        return bankAccountRepository.findByCardNumber(cardNumber).orElseThrow(
                () -> new NotValidInformation(String.format("Card with Number %s Not found",cardNumber))
        );
    }
    public void withdraw(String cardNumber,Long amount){
        bankAccountRepository.withdraw(cardNumber, amount);
    }
}
