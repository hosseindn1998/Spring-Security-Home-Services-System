package ir.hosseindn.dto.order;

import ir.hosseindn.dto.customer.CustomerId;
import ir.hosseindn.dto.customer.CustomerSaveRequest;
import ir.hosseindn.dto.subservice.SubServiceId;
import ir.hosseindn.dto.subservice.SubServiceSaveRequest;
import ir.hosseindn.model.SubService;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderSaveRequest(
        Long subserviceId,
        Long customerId,
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
