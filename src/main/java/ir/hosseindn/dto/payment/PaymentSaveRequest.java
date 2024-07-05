package ir.hosseindn.dto.payment;

public record PaymentSaveRequest(
        String cardNumber,
        String cvv,
        String mm,
        String yy,
        String password,
        String captchaAnswer
) {
}
