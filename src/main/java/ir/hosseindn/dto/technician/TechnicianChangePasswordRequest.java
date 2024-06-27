package ir.hosseindn.dto.technician;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record TechnicianChangePasswordRequest(
        @Email(message = "Email must be valid")
        String email,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8}$",message ="Password Not valid ")
        String password,
        String confirmPassword
) {
}
