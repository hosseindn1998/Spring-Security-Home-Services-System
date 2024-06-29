package ir.hosseindn.dto.offer;

import ir.hosseindn.dto.order.OrderId;
import ir.hosseindn.dto.technician.TechnicianId;

public record OfferSaveRequest(
        OrderId odrer,

        OfferSaveRequestWithoutFKs offer,
        TechnicianId technician
) {
}
