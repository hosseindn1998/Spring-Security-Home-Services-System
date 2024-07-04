package ir.hosseindn.dto.subservice;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SubServiceWithoutMainService(
        @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$"
                ,message = "Could be 1-29 char and contain only alphabet")
        String name,
        @Min(0)
        @NotNull
        Long basePrice,
        @Pattern(regexp = "^[A-Z](?=.{1,55}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$"
                ,message = "Could be 1-55 char and contain only alphabet")
        String description
) {
}
