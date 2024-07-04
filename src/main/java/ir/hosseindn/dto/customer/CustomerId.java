package ir.hosseindn.dto.customer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CustomerId(
        @NotNull
        @Min(0L)
        Long id
) {
}
