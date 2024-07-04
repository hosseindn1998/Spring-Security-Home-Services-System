package ir.hosseindn.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record AdminLoginRequest(
        @Email(message = "Email must be valid")
        String email,

        String password
) {
}
