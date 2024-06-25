package ir.hosseindn.mapper.order;

import ir.hosseindn.dto.order.OrderSaveRequest;
import ir.hosseindn.dto.order.OrderSaveResponse;
import ir.hosseindn.mapper.subservice.SubServiceMapper;
import ir.hosseindn.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order orderSaveRequestToModel(OrderSaveRequest request);

    OrderSaveResponse modelToOrderSaveResponse(Order order);
}
