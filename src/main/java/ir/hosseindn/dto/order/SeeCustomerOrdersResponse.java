package ir.hosseindn.dto.order;

import ir.hosseindn.dto.customer.CustomerId;
import ir.hosseindn.dto.subservice.SubServiceId;

import java.time.LocalDateTime;

public record SeeCustomerOrdersResponse(
        Long id,
        SubServiceId subservice,
        CustomerId customer,
        Long suggestedPrice,
        String description,
        LocalDateTime dateForDo,
        String address
) {
}
