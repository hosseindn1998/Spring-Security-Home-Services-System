package ir.hosseindn.dto.customer;

import java.time.LocalDate;

public record CustomerSaveResponse(
        Long id,
        String firstName,
        String lastName,
        String nationalCode,
        String email,
        LocalDate registeredDate
) {
}
