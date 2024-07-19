package ir.hosseindn.dto.offer;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record OfferSaveRequest(
        Long odrerId,
        @Future
        LocalDateTime dateOfOfferToStart,
        @Min(0)
        @NotNull
        Long suggestPrice,
        @Future
        LocalDateTime dateOfOfferToDone
) {
}
