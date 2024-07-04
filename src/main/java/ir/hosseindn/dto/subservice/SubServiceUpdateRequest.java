package ir.hosseindn.dto.subservice;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SubServiceUpdateRequest(
        @NotNull
        @Min(0L)
        Long id,
        @NotNull
        @Min(0L)
        Long basePrice,
        @Pattern(regexp = "^[A-Z](?=.{1,55}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$"
                ,message = "Could be 1-55 char and contain only alphabet")
        String description
) {
}
