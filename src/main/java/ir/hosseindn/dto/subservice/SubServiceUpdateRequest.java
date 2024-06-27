package ir.hosseindn.dto.subservice;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SubServiceUpdateRequest(
        Long id,
        Long basePrice,
        String description
) {
}
