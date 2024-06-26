package ir.hosseindn.dto.order;

import ir.hosseindn.dto.offer.OfferSaveRequest;

public record OrderChooseOfferRequest(
        Long id,
        OfferSaveRequest offer
) {
}
