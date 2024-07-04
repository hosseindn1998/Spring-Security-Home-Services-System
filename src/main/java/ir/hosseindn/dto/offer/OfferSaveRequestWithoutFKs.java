package ir.hosseindn.dto.offer;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.aspectj.lang.annotation.After;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record OfferSaveRequestWithoutFKs(
        @Future
        LocalDateTime dateOfOfferToStart,
        @Min(0)
        @NotNull
        Long suggestPrice,
        @Future
        LocalDateTime dateOfOfferToDone,
        Boolean isAccepted
        ) {
}
