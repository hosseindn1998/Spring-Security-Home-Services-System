package ir.hosseindn.dto.offer;

import ir.hosseindn.model.Technician;

import java.time.LocalDate;

public record OfferFindByOrderResponse(
        LocalDate dateOfOfferToStart,
        Long suggestPrice,
        LocalDate dateOfOfferToDone,
        Technician technician,
        Boolean isAccepted
) {
}
