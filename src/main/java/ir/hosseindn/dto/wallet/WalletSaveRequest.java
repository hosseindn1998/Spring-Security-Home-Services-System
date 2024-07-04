package ir.hosseindn.dto.wallet;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record WalletSaveRequest(
        @NotNull
        @Min(0L)
        Long amount
) {
}
