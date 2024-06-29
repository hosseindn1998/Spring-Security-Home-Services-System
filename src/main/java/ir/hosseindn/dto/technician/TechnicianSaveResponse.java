package ir.hosseindn.dto.technician;

import java.time.LocalDate;

public record TechnicianSaveResponse(
        Long id,
        String firstName,
        String lastName,
        String nationalCode,
        String email,
        LocalDate registeredDate,
        Double rate
) {
}
