package ir.hosseindn.service.paymenttransaction;

import com.mewebstudio.captcha.Captcha;
import com.mewebstudio.captcha.GeneratedCaptcha;
import ir.hosseindn.dto.payment.PaymentSaveRequest;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.PaymentTransaction;
import ir.hosseindn.model.enums.OrderStatus;
import ir.hosseindn.repository.paymenttransaction.PaymentTransactionRepository;
import ir.hosseindn.service.bankaccount.BankAccountService;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentTransactionService {
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final BankAccountService bankAccountService;
    @Autowired
    @Lazy
    private OrderService orderService;
    private final WalletService walletService;

    public PaymentTransaction save(PaymentTransaction paymentTransaction) {
        return paymentTransactionRepository.save(paymentTransaction);
    }

    public BufferedImage updateCaptchaAnswer(Long paymentTransactionId) {
        com.mewebstudio.captcha.Captcha captcha = new Captcha();
        GeneratedCaptcha generatedCaptcha = captcha.generate();
        BufferedImage captchaImage = generatedCaptcha.getImage();
        String captchaCode = generatedCaptcha.getCode();
        paymentTransactionRepository.updateCaptchaAnswer(paymentTransactionId, captchaCode);
        return captchaImage;
    }

    public PaymentTransaction findById(Long id) {
        return paymentTransactionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("transaction not found")
        );
    }

    public PaymentTransaction paymentTransactionBuilder(PaymentSaveRequest request, Long ptId) {
        if (!bankAccountService.isValidCardInfo(request.cardNumber(), request.cvv(), request.mm(),
                request.yy(), request.password()))
            throw new NotValidInformation("Card Information Not Valid");
        PaymentTransaction preparedTransaction = findById(ptId);
        if (preparedTransaction.getCaptchaAnswer() == null)
            throw new NotFoundException("Captcha not found ,click on captcha link to generate captcha");
        if (!request.captchaAnswer().equals(preparedTransaction.getCaptchaAnswer()))
            throw new NotValidInformation("Captcha Answer is invalid");
        Order order = orderService.findById(preparedTransaction.getOrderId());
        preparedTransaction.setSuggestPrice(order.getChoosedOffer().getSuggestPrice());
        preparedTransaction.setCardNumber(request.cardNumber());
        preparedTransaction.setCvv(request.cvv());
        preparedTransaction.setMm(request.mm());
        preparedTransaction.setYy(request.yy());
        preparedTransaction.setPassword(request.password());
        preparedTransaction.setTime(LocalDateTime.now());
        bankAccountService.withdraw(request.cardNumber(), preparedTransaction.getSuggestPrice());
        walletService.deposit(order.getCustomer().getWallet().getId(), preparedTransaction.getSuggestPrice());
        walletService.payFromWallet(order.getCustomer().getWallet(),
                order.getTechnician().getWallet(), preparedTransaction.getSuggestPrice());
        orderService.changeOrderStatusToPaid(order);
        order.setOrderStatus(OrderStatus.Paid);
        return save(preparedTransaction);
    }


}
