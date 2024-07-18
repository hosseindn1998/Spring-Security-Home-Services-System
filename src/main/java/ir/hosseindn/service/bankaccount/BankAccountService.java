package ir.hosseindn.service.bankaccount;

import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.BankAccount;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.OrderStatus;
import ir.hosseindn.model.PaymentTransaction;
import ir.hosseindn.repository.bankaccount.BankAccountRepository;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.paymenttransaction.PaymentTransactionService;
import ir.hosseindn.service.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public Boolean isValidCardInfo(String cardNumber, String cvv, String mm, String yy, String password) {
        return bankAccountRepository.existsByCardNumberAndCvvAndMmAndYyAndPassword(cardNumber, cvv, mm, yy, password);
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
