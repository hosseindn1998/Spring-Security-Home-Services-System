package ir.hosseindn.controller.order;

import ir.hosseindn.dto.order.OrderSaveRequest;
import ir.hosseindn.dto.order.OrderSaveResponse;
import ir.hosseindn.dto.subservice.SubServiceSaveRequest;
import ir.hosseindn.dto.subservice.SubServiceSaveResponse;
import ir.hosseindn.mapper.order.OrderMapper;
import ir.hosseindn.mapper.subservice.SubServiceMapper;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.SubService;
import ir.hosseindn.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
}
