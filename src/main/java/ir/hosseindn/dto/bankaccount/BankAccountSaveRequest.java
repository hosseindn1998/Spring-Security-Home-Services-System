package ir.hosseindn.dto.bankaccount;

import jakarta.validation.constraints.Min;

public record BankAccountSaveRequest(
        String cardNumber,
        String cvv,
        String mm,
        String yy,
        String password,
        @Min(value = 0,message = "Your bank account balance is insufficient ")
        Long amount
) {
}
