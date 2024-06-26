package ir.hosseindn.mapper.order;

import ir.hosseindn.dto.order.*;
import ir.hosseindn.mapper.subservice.SubServiceMapper;
import ir.hosseindn.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order orderSaveRequestToModel(OrderSaveRequest request);

    OrderSaveResponse modelToOrderSaveResponse(Order order);
    Order orderChooseOfferToModel(OrderChooseOfferRequest request);
    OrderChooseOfferResponse modelToOrderChooseOffer(Order order);
    Order orderChangeStatusToWaitForComeRequestToModel(OrderChangeStatusToWaitForComeRequest request);
    OrderChangeStatusToWaitForComeResponse modelToOrderChangeStatusToWaitForComeResponse(Order oder);
}
