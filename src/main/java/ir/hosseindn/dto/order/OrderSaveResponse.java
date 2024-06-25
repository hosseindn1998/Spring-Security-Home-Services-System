package ir.hosseindn.dto.order;

import ir.hosseindn.dto.customer.CustomerSaveRequest;
import ir.hosseindn.model.SubService;

import java.time.LocalDate;

public record OrderSaveResponse(
        SubService subservice,
        CustomerSaveRequest customer,
        Long suggestedPrice,

        Boolean isPaid,

        String description,

        LocalDate dateForDo,

        String address
) {
}
