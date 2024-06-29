package ir.hosseindn.dto.offer;

import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.dto.technician.TechnicianSaveResponse;

import java.time.LocalDate;

public record OfferFindByOrderResponse(
        LocalDate dateOfOfferToStart,
        Long suggestPrice,
        LocalDate dateOfOfferToDone,
        TechnicianSaveResponse technician,
        Boolean isAccepted
) {
}
