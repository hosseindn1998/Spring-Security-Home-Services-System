package ir.hosseindn.dto.technician;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

public record TechnicianSaveRequest(
        @NotNull(message = "FirstName must not be null")
        @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
        String firstName,
        @NotNull(message = "LastName must not be null")
        @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
        String lastName,
        @Pattern(regexp = "[0-9]{10}")
        String nationalCode,
        @NotNull
        @Email(message = "Email must be valid")
        String email,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8}$")
        String password,
        MultipartFile imageFile
) {
}
