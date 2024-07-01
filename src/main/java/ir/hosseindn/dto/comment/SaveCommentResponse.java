package ir.hosseindn.dto.comment;

import ir.hosseindn.dto.order.OrderId;
import ir.hosseindn.dto.technician.TechnicianId;

public record SaveCommentResponse(
        Integer rate,
        String description,
        OrderId order,
        TechnicianId technician
) {
}
