package ir.hosseindn.dto.order;

import ir.hosseindn.dto.customer.CustomerId;
import ir.hosseindn.dto.subservice.SubServiceId;
import ir.hosseindn.model.OrderStatus;

import java.time.LocalDateTime;

public record OrderSearchItemResponse(
        Long id,
        SubServiceId subservice,
        CustomerId customer,
        Long suggestedPrice,
        String description,
        LocalDateTime creationDate,
        LocalDateTime dateForDo,
        String address,
        OrderStatus orderStatus

) {
}
