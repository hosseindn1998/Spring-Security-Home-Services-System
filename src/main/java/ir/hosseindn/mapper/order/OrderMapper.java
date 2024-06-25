package ir.hosseindn.mapper.order;

import ir.hosseindn.dto.order.OrderSaveRequest;
import ir.hosseindn.dto.order.OrderSaveResponse;
import ir.hosseindn.model.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    Order orderSaveRequestToModel(OrderSaveRequest request);

    OrderSaveResponse modelToOrderSaveResponse(Order order);
}
