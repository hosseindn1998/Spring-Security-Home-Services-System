package ir.hosseindn.dto.paymenttransaction;

import java.time.LocalDateTime;

public record PaymentTransactionSaveResponse(
        Long id,
        LocalDateTime time
) {
}
