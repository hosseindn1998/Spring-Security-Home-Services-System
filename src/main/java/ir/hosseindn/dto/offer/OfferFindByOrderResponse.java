package ir.hosseindn.dto.offer;

import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.dto.technician.TechnicianSaveResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OfferFindByOrderResponse(
        LocalDateTime dateOfOfferToStart,
        Long suggestPrice,
        LocalDateTime dateOfOfferToDone,
        TechnicianSaveResponse technician,
        Boolean isAccepted
) {
}
