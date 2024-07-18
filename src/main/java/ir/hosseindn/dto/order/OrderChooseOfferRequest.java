package ir.hosseindn.dto.order;

public record OrderChooseOfferRequest(
        Long orderId,
        Long offerId
) {
}
