package ir.hosseindn.mapper.order;

import ir.hosseindn.dto.order.*;
import ir.hosseindn.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order orderSaveRequestWithoutFKsToModel(OrderSaveRequestWithoutFKs request);

    OrderSaveResponse modelToOrderSaveResponse(Order order);
    Order orderChooseOfferToModel(OrderChooseOfferRequest request);
    OrderChooseOfferResponse modelToOrderChooseOffer(Order order);
    Order orderChangeStatusRequestToModel(OrderChangeStatusRequest request);
    OrderChangeStatusResponse modelToOrderChangeStatusResponse(Order oder);
}
