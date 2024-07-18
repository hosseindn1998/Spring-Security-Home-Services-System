package ir.hosseindn.dto.order;

import ir.hosseindn.dto.offer.OfferSaveResponse;

public record OrderChooseOfferResponse(
        OfferSaveResponse choosedOffer
) {
}
