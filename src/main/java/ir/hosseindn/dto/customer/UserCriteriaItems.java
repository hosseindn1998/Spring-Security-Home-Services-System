package ir.hosseindn.dto.customer;

import ir.hosseindn.model.Roles;
import ir.hosseindn.model.TechnicianStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserCriteriaItems(

        Roles role,
        Long id,
        String firstName,
        String lastName,
        String nationalCode,
        String email,
        String password,
        LocalDateTime registeredDate,
        TechnicianStatus technicianStatus,
        double rate,
        int totalScores,
        int countScores,
        boolean isActive

) {
}
