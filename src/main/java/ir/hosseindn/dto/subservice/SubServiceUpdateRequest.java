package ir.hosseindn.dto.subservice;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SubServiceUpdateRequest(
        @Column(unique = true)
        String name,
        @Min(0)
        Long newBasePrice,
        @NotNull
        String newDescription
) {
}
