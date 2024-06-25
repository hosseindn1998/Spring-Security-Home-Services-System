package ir.hosseindn.dto.subservice;

import ir.hosseindn.dto.mainservice.MainServiceSaveRequest;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SubServiceSaveRequest(
        @Column(unique = true)
        String name,
        @Min(0)
        Long basePrice,
        @NotNull
        String description,
        MainServiceSaveRequest mainService
) {
}
