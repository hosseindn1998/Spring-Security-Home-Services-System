package ir.hosseindn.dto.paymenttransaction;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record PaymentTransactionSaveRequest(
        @NotNull
        @Min(0L)
        Long orderId,
        @NotNull
        @Min(0L)
        Long suggestPrice,
        @Pattern(regexp = "^(\\d{4}[- ]){3}\\d{4}|\\d{16}$")
        String cardNumber,
        @Pattern(regexp = "^([0-9]{3,4})$")
        String cvv,
        @Pattern(regexp = "^((0[1-9])|(1[0-2]))$")
        String mm,
        @Pattern(regexp = "^(([1-9][0-9])|(0[1-9]))$")
        String yy,
        @Pattern(regexp = "^([0-9]{5,6})$")
        String password
) {
}
