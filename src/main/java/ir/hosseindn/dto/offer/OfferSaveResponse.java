package ir.hosseindn.dto.offer;

import ir.hosseindn.dto.order.OrderId;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Technician;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record OfferSaveResponse(
        Long id,
        OrderId odrer,
        LocalDate dateOfOfferToStart,
        @Min(0)
        @NotNull
        Long suggestPrice,
        LocalDate dateOfOfferToDone,
        @ManyToOne
        TechnicianId technician,
        Boolean isAccepted
) {
}
