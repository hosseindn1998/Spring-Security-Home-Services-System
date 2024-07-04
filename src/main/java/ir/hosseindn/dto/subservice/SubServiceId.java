package ir.hosseindn.dto.subservice;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SubServiceId(
        @NotNull
        @Min(0L)
        Long id
) {
}
