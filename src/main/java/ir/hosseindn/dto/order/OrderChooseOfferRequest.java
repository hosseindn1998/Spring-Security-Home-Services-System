package ir.hosseindn.dto.order;

import ir.hosseindn.dto.offer.OfferSaveRequest;

public record OrderChooseOfferRequest(
        OrderSaveRequest order,
        OfferSaveRequest offer
) {
}
