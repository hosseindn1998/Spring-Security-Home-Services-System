package ir.hosseindn.dto.customer;

import ir.hosseindn.model.Roles;

import java.time.LocalDateTime;

public record CustomerSaveResponse(
        Long id,
        String firstName,
        String lastName,
        String nationalCode,
        String email,
        LocalDateTime registeredDate,
        Roles role
) {
}
