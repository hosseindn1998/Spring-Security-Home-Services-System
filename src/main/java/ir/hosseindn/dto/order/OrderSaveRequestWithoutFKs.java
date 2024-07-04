package ir.hosseindn.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public record OrderSaveRequestWithoutFKs(
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
