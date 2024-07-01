package ir.hosseindn.dto.order;

import ir.hosseindn.dto.customer.CustomerId;
import ir.hosseindn.dto.offer.OfferId;
import ir.hosseindn.dto.subservice.SubServiceId;
import ir.hosseindn.model.Offer;
import ir.hosseindn.model.OrderStatus;

import java.time.LocalDate;

public record OrderChangeStatusResponse(
        SubServiceId subservice,
        CustomerId customer,
        Long suggestedPrice,

        String description,

        LocalDate dateForDo,
        OrderStatus orderStatus,

        String address,
        OfferId choosedOffer
) {
}
