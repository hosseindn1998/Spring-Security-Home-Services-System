package ir.hosseindn.dto.technician;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record TechnicianVerifyRequest(
        @NotNull
        @Email(message = "Email must be valid")
        String email
) {
}
