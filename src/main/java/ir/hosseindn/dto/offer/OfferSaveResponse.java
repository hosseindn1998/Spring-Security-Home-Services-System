package ir.hosseindn.dto.offer;

import ir.hosseindn.dto.order.OrderId;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Technician;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record OfferSaveResponse(
        Long id,
        OrderId odrer,
        LocalDateTime dateOfOfferToStart,
        Long suggestPrice,
        LocalDateTime dateOfOfferToDone,
        TechnicianId technician,
        Boolean isAccepted
) {
}
