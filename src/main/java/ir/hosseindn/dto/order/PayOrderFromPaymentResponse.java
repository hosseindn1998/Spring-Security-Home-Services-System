package ir.hosseindn.dto.order;

import ir.hosseindn.dto.customer.CustomerId;
import ir.hosseindn.dto.offer.OfferId;
import ir.hosseindn.dto.subservice.SubServiceId;
import ir.hosseindn.model.enums.OrderStatus;

import java.time.LocalDateTime;

public record PayOrderFromPaymentResponse(
        SubServiceId subservice,
        CustomerId customer,
        Long suggestedPrice,

        String description,

        LocalDateTime dateForDo,
        OrderStatus orderStatus,

        String address,
        OfferId choosedOffer
) {
}
