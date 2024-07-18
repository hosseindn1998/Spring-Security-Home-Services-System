package ir.hosseindn.dto.order;

import ir.hosseindn.model.OrderStatus;

import java.time.LocalDateTime;

public record OrderSearchItemsRequest(
        Long id,
        Long customerId,
        Long technicianId,
        LocalDateTime dateForDo,
        OrderStatus orderStatus,
        String subService,
        String mainService
) {
}
