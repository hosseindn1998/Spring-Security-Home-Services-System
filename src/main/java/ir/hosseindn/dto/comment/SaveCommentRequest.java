package ir.hosseindn.dto.comment;

import ir.hosseindn.dto.order.OrderId;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Technician;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SaveCommentRequest(
        @NotNull
        @Min(1)
        @Max(5)
        Integer rate,
        String description,
        @OneToOne
        OrderId order,
        @ManyToOne
        TechnicianId technician
        ) {
}
