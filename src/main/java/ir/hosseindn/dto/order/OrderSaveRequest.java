package ir.hosseindn.dto.order;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record OrderSaveRequest(
        Long subserviceId,
        @Min(0L)
        @NotNull
        Long suggestedPrice,
        @NotNull
        @Pattern(regexp = "^[A-Z](?=.{1,55}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
        String description,
        @NotNull
        @Future
        LocalDateTime dateForDo,
        @NotNull
        @Pattern(regexp = "^[A-Z](?=.{1,55}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
        String address
) {
}
