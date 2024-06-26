package ir.hosseindn.dto.offer;

import ir.hosseindn.dto.order.OrderSaveRequest;

public record OfferFindByOrderRequest(
        OrderSaveRequest order
) {
}
