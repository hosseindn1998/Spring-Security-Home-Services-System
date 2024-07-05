package ir.hosseindn.controller.payment;

import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.BankAccount;
import ir.hosseindn.model.Captcha;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.PaymentTransaction;
import ir.hosseindn.service.bankaccount.BankAccountService;
import ir.hosseindn.service.captcha.CaptchaService;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.paymenttransaction.PaymentTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class ShowFormController {
    private final PaymentTransactionService paymentTransactionService;
    private final OrderService orderService;
    private final BankAccountService bankAccountService;
    private final CaptchaService captchaService;
    private Long ptId;

    @GetMapping()
    public String getHomePage(Model model) {

        Order order = orderService.findById(1L);
        Long suggestPrice = order.getChoosedOffer().getSuggestPrice();
        PaymentTransaction paymentTransaction = paymentTransactionService.save(new PaymentTransaction());
        paymentTransaction.setSuggestPrice(suggestPrice);
        model.addAttribute("paymentTransactionId","/payment/image?paymentTransactionId="+paymentTransaction.getId());
        model.addAttribute("suggestPrice", suggestPrice);


        return "payment";
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam Long paymentTransactionId) {
        ptId=paymentTransactionId;
        long randomLong = 1 + (long) (Math.random() * (4));
        Captcha captcha = captchaService.findById(randomLong);
        byte[] imageData = captcha.getPicture();
        paymentTransactionService.updateCaptchaAnswer(paymentTransactionId, captcha.getAnswer());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/images/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource downloadImage(@RequestParam Long imageId) {
        byte[] image = captchaService.findById(imageId).getPicture();

        return new ByteArrayResource(image);
    }

    @GetMapping ("/pay-submit")
    public String getCardInfoPage(
            @RequestParam String cardNumber,
            @RequestParam String cvv,
            @RequestParam String mm,
            @RequestParam String yy,
            @RequestParam String password,
            @RequestParam String captchaAnswer,
            Model model
    ) {

        model.addAttribute("captchaAnswer", captchaAnswer);
        bankAccountService.findIfValidAccountInformation(cardNumber, cvv, mm, yy, password);
        PaymentTransaction byId = paymentTransactionService.findById(ptId);
        if (byId.getCaptchaAnswer() == null)
            throw new NotFoundException("Captcha not found ,click on captcha link to generate captcha");
        if (!captchaAnswer.equals(byId.getCaptchaAnswer()))
            throw new NotValidInformation("Captcha Answer is invalid");
        byId.setSuggestPrice(100L);
        byId.setCardNumber(cardNumber);
        byId.setCvv(cvv);
        byId.setMm(mm);
        byId.setYy(yy);
        byId.setPassword(password);
        byId.setTime(LocalDateTime.now());
        bankAccountService.withdraw(cardNumber, byId.getSuggestPrice());
        paymentTransactionService.save(byId);
        return "pay_successfully";
    }

}
