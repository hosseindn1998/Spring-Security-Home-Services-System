package ir.hosseindn.dto.offer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OfferId(
        @NotNull
        @Min(0L)
        Long id
) {
}
