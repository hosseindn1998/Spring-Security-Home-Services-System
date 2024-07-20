package ir.hosseindn.dto.customer;

import ir.hosseindn.model.enums.Role;

import java.time.LocalDateTime;

public record CustomerSaveResponse(
        Long id,
        String firstName,
        String lastName,
        String nationalCode,
        String email,
        LocalDateTime registeredDate,
        Role role
) {
}
