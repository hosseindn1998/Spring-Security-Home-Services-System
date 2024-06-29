package ir.hosseindn.dto.offer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record OfferSaveRequestWithoutFKs(
        LocalDate dateOfOfferToStart,
        @Min(0)
        @NotNull
        Long suggestPrice,

        LocalDate dateOfOfferToDone,

        Boolean isAccepted
        ) {
}
