package ir.hosseindn.dto.technician;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TechnicianId(
        @NotNull
        @Min(0L)
        Long id
) {
}
