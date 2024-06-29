package ir.hosseindn.dto.order;

import ir.hosseindn.dto.customer.CustomerId;
import ir.hosseindn.dto.subservice.SubServiceId;

import java.time.LocalDate;

public record OrderChangeStatusResponse(
        SubServiceId subservice,
        CustomerId customer,
        Long suggestedPrice,

        Boolean isPaid,

        String description,

        LocalDate dateForDo,

        String address
) {
}
