package ir.hosseindn.dto.order;

import ir.hosseindn.dto.customer.CustomerId;
import ir.hosseindn.dto.customer.CustomerSaveRequest;
import ir.hosseindn.dto.subservice.SubServiceId;
import ir.hosseindn.dto.subservice.SubServiceSaveRequest;
import ir.hosseindn.model.SubService;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record OrderSaveRequest(
        SubServiceId subserviceId,
        CustomerId customerId,
       OrderSaveRequestWithoutFKs order) {
}
