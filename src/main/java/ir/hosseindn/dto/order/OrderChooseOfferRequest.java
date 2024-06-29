package ir.hosseindn.dto.order;

import ir.hosseindn.dto.offer.OfferId;

public record OrderChooseOfferRequest(
        OrderId order,
        OfferId offer
) {
}
