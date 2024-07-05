package ir.hosseindn.service.paymenttransaction;

import ir.hosseindn.exception.NotFoundException;
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
    public void updateCaptchaAnswer(Long id,String newAnswer){
        paymentTransactionRepository.updateCaptchaAnswer(id,newAnswer);
    }
    public PaymentTransaction findById(Long id){
        return paymentTransactionRepository.findById(id).orElseThrow(
                ()->new NotFoundException("transaction not found")
        );
    }


}
