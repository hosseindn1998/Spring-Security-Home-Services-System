package ir.hosseindn.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record AdminLoginRequest(
        @Email(message = "Email must be valid")
        String email,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8}$", message = "Password Not valid ")
        String password
) {
}
