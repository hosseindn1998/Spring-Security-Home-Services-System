package ir.hosseindn.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record OrderSaveRequestWithoutFKs(
        @Min(0L)
        Long suggestedPrice,
        @NotNull
        Boolean isPaid,
        @NotNull
        @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
        String description,
        @NotNull
        LocalDate dateForDo,
        @NotNull
        @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
        String address
) {
}
