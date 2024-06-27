package ir.hosseindn.dto.order;

import ir.hosseindn.dto.customer.CustomerId;
import ir.hosseindn.dto.customer.CustomerSaveRequest;
import ir.hosseindn.dto.customer.CustomerSaveResponse;
import ir.hosseindn.dto.subservice.SubServiceId;
import ir.hosseindn.dto.subservice.SubServiceSaveResponse;
import ir.hosseindn.model.SubService;

import java.time.LocalDate;

public record OrderSaveResponse(
        SubServiceId subservice,
        CustomerId customer,
        Long suggestedPrice,

        Boolean isPaid,

        String description,

        LocalDate dateForDo,

        String address
) {
}
