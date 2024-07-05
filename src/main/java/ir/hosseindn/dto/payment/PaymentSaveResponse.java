package ir.hosseindn.dto.payment;

import java.time.LocalDateTime;

public record PaymentSaveResponse(
        String cardNumber,
        LocalDateTime time
) {
}
