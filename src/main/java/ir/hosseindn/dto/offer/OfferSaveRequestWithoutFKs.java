package ir.hosseindn.dto.offer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record OfferSaveRequestWithoutFKs(
        LocalDateTime dateOfOfferToStart,
        @Min(0)
        @NotNull
        Long suggestPrice,

        LocalDateTime dateOfOfferToDone,

        Boolean isAccepted
        ) {
}
