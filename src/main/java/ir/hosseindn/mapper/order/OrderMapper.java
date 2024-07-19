package ir.hosseindn.mapper.order;

import ir.hosseindn.dto.order.*;
import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import org.aspectj.weaver.ast.Or;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order orderSaveRequestToModel(OrderSaveRequest request);

    OrderSaveResponse modelToOrderSaveResponse(Order order);
    Order orderChooseOfferToModel(OrderChooseOfferRequest request);
    OrderChooseOfferResponse modelToOrderChooseOfferResponse(Order order);
    Order orderChangeStatusRequestToModel(OrderChangeStatusRequest request);
    OrderChangeStatusResponse modelToOrderChangeStatusResponse(Order oder);
    Order orderIdToModel(Long orderId);
    Order payOrderFromWalletRequestToModel(PayOrderFromWalletRequest request);
    PayOrderFromWalletResponse modelToPayOrderFromWalletResponse (Order order);
    Order payOrderFromPaymentRequestToModel(PayOrderFromPaymentRequest request);
    PayOrderFromPaymentResponse modelToPayOrderFromPaymentResponse(Order order);
    List<SeeCustomerOrdersResponse> modelListToSeeCustomerOrdersResponse(List<Order> orderList);
    List<SeeTechnicianOrdersResponse> modelListToSeeTechnicianOrdersResponse(List<Order> orderList);
    List<OrderSaveResponse> modelListToOrderSaveResponseList(List<Order> orderList);
    Order orderSearchItemToModel(OrderSearchItemsRequest request);
    List<OrderSearchItemResponse> modelToOrderSearchItemResponse(List<Order> orderList);
}
