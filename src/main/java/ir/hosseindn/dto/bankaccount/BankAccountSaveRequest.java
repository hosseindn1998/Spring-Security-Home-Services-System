package ir.hosseindn.dto.bankaccount;

import jakarta.validation.constraints.Min;

public record BankAccountSaveRequest(
        @Min(value = 0,message = "Your bank account balance is insufficient ")
        Long amount
) {
}
