package ir.hosseindn.dto.wallet;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record WalletId(
        @NotNull
                @Min(0L)
        Long id
) {
}
