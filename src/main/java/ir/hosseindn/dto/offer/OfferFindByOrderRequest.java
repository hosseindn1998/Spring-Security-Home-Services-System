package ir.hosseindn.dto.offer;

import ir.hosseindn.dto.order.OrderId;

public record OfferFindByOrderRequest(
        OrderId odrer
) {
}
