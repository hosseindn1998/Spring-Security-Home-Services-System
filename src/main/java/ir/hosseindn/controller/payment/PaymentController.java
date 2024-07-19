package ir.hosseindn.controller.payment;

import ir.hosseindn.dto.payment.PaymentSaveRequest;
import ir.hosseindn.model.PaymentTransaction;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.paymenttransaction.PaymentTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentTransactionService paymentTransactionService;
    private final OrderService orderService;
    private Long ptId;


    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping()
    public String cardFormPage(@Valid @RequestParam Long orderId, Model model) {
        PaymentTransaction paymentTransaction = orderService.payOrderFromPayment(orderId);
        ptId = paymentTransaction.getId();
        model.addAttribute("paymentTransactionId", "/payment/image?paymentTransactionId=" + paymentTransaction.getId());
        model.addAttribute("suggestPrice", paymentTransaction.getSuggestPrice());
        return "payment";
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/image")
    public ResponseEntity<byte[]> getCaptchaImage() throws IOException {
        BufferedImage imageData = paymentTransactionService.updateCaptchaAnswer(ptId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imageData, "png", baos);
        byte[] bytes = baos.toByteArray();
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/pay-submit")
    public String getCardInfo(@Valid @RequestBody PaymentSaveRequest request, Model model) {
        PaymentTransaction paymentTransaction = paymentTransactionService.paymentTransactionBuilder(request, ptId);
        model.addAttribute("id", paymentTransaction.getId());
        model.addAttribute("time", paymentTransaction.getTime());
        return "pay_submit";
    }

}
