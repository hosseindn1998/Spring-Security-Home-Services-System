package ir.hosseindn.service.paymenttransaction;

import ir.hosseindn.model.PaymentTransaction;
import ir.hosseindn.repository.paymenttransaction.PaymentTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTransactionService {
private final PaymentTransactionRepository paymentTransactionRepository;
    public PaymentTransaction save(PaymentTransaction paymentTransaction){
        return paymentTransactionRepository.save(paymentTransaction);
    }


}
