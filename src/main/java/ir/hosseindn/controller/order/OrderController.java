package ir.hosseindn.controller.order;

import ir.hosseindn.dto.order.*;
import ir.hosseindn.mapper.order.OrderMapper;
import ir.hosseindn.model.Order;
import ir.hosseindn.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/add-order")
    public ResponseEntity<OrderSaveResponse> addOrder (@Validated @RequestBody OrderSaveRequest request){
        Order mappedOrder= OrderMapper.INSTANCE.orderSaveRequestToModel(request);
        Order savedOrder=orderService.save(mappedOrder);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderSaveResponse(savedOrder), HttpStatus.CREATED);
    }
    @PatchMapping("/chooseoffer")
    public ResponseEntity<OrderChooseOfferResponse>chooseOffer(@Validated @RequestBody OrderChooseOfferRequest request){
        Order mappedOrder=OrderMapper.INSTANCE.orderChooseOfferToModel(request);
        Order updatedOrder=orderService.chooseOffer(mappedOrder.getId(),mappedOrder.getChoosedOffer());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderChooseOffer(updatedOrder),HttpStatus.OK);
    }
    @PatchMapping("/order-status-to-started")
    public ResponseEntity<OrderChangeStatusResponse>orderStatusToStarted(@Validated @RequestBody OrderChangeStatusRequest request){
        Order mappedOrder=OrderMapper.INSTANCE.orderChangeStatusRequestToModel(request);
        Order updatedOrder=orderService.changeOrderStatusToStarted(mappedOrder.getId(),mappedOrder.getChoosedOffer());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderChangeStatusResponse(updatedOrder),HttpStatus.OK);
    }
    @PatchMapping("/order-status-to-done")
    public ResponseEntity<OrderChangeStatusResponse>orderStatusToDone(@Validated @RequestBody OrderChangeStatusRequest request){
        Order mappedOrder=OrderMapper.INSTANCE.orderChangeStatusRequestToModel(request);
        Order updatedOrder=orderService.changeOrderStatusToStarted(mappedOrder.getId(),mappedOrder.getChoosedOffer());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderChangeStatusResponse(updatedOrder),HttpStatus.OK);
    }
}
