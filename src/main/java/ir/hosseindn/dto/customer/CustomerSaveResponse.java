package ir.hosseindn.dto.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerSaveResponse(
        Long id,
        String firstName,
        String lastName,
        String nationalCode,
        String email,
        LocalDateTime registeredDate
) {
}
