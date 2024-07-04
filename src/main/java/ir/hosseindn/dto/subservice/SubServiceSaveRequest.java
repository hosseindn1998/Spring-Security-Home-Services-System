package ir.hosseindn.dto.subservice;

import ir.hosseindn.dto.mainservice.MainServiceSaveRequest;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SubServiceSaveRequest(

        SubServiceWithoutMainService subService,
        @NotNull
        @Min(0L)
        Long mainServiceId
) {
}
