package ir.hosseindn.dto.technician;

import ir.hosseindn.model.enums.Role;
import ir.hosseindn.model.TechnicianStatus;

import java.time.LocalDateTime;

public record TechnicianSaveResponse(
        Long id,
        String firstName,
        String lastName,
        String nationalCode,
        String email,
        LocalDateTime registeredDate,
        Double rate,
        Integer totalScores,
        Long countScores,
        TechnicianStatus technicianStatus,
        Boolean isActive,
        Role role
) {
}
