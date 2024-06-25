package ir.hosseindn.dto.technician;

import ir.hosseindn.dto.wallet.WalletSaveRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;
import org.springframework.context.annotation.Lazy;

public record TechnicianSaveRequest(
        @NotNull(message = "FirstName must not be null")
        @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
        String firstName,
        @NotNull(message = "LastName must not be null")
        @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
        String lastName,
        @Pattern(regexp = "[0-9]{10}")
        @Column(unique = true)
        String nationalCode,
        @NotNull
        @Column(unique = true)
        @Email(message = "Email must be valid")
        String email,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8}$")
        String password,
        @Lob
        @ToString.Exclude
        @Lazy
        byte[] avatar,
        WalletSaveRequest wallet
) {
}
