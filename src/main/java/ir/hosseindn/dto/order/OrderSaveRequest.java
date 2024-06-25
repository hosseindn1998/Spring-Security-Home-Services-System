package ir.hosseindn.dto.order;

import ir.hosseindn.dto.customer.CustomerSaveRequest;
import ir.hosseindn.model.SubService;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record OrderSaveRequest(
        SubService subservice,
        @ManyToOne
        CustomerSaveRequest customer,
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
        String address) {
}
