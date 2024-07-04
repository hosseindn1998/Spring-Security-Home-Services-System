package ir.hosseindn.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderId(
        @NotNull
        @Min(0L)
        Long id
) {
}
