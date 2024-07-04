package ir.hosseindn.dto.user;

import ir.hosseindn.model.Roles;
import ir.hosseindn.model.TechnicianStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record UserCriteriaItems(
        @Min(0L)
        @Max(2L)
        Roles role,
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
        @Past
        LocalDateTime registeredDate,
        @NotNull
        @Min(0L)
        @Max(2L)
        TechnicianStatus technicianStatus,
        @NotNull
        @Min(0)
        @Max(5)
        Double rate,
        @NotNull
        @Min(0)
        Integer totalScores,
        @NotNull
        @Min(0)
        Integer countScores,
        Boolean isActive

) {
}
