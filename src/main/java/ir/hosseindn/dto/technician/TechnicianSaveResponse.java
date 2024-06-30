package ir.hosseindn.dto.technician;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TechnicianSaveResponse(
        Long id,
        String firstName,
        String lastName,
        String nationalCode,
        String email,
        LocalDateTime registeredDate,
        Double rate,
        int TotalScores,
        long countScores,
        boolean isActive
) {
}
