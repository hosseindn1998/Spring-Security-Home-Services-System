package ir.hosseindn.controller.order;

import ir.hosseindn.dto.order.*;
import ir.hosseindn.mapper.order.OrderMapper;
import ir.hosseindn.model.Order;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.techniciansubservice.TechnicianSubServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final TechnicianSubServiceService technicianSubServiceService;

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/add-order")
    public ResponseEntity<OrderSaveResponse> addOrder(@Valid @RequestBody OrderSaveRequest request,Principal principal) {
        Order mappedOrder = OrderMapper.INSTANCE.orderSaveRequestToModel(request);
        Order savedOrder = orderService.addOrderByCustomer(mappedOrder,principal.getName(),request.subserviceId());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderSaveResponse(savedOrder), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PatchMapping("/choose-offer")
    public ResponseEntity<OrderChooseOfferResponse> chooseOffer(@Valid @RequestBody OrderChooseOfferRequest request) {
        Order updatedOrder = orderService.chooseOffer(request.orderId(), request.offerId());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderChooseOfferResponse(updatedOrder), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PatchMapping("/see-customers-orders-list")
    public ResponseEntity<List<SeeCustomerOrdersResponse>> seeOrderListForCustomer(Principal principal){
        List<Order> orderList = orderService.seeAllByCustomer(principal.getName());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelListToSeeCustomerOrdersResponse(orderList),HttpStatus.FOUND);
    }

    @PreAuthorize("hasRole('ROLE_TECHNICIAN')")
    @PatchMapping("/see-technicians-orders-list")
    public ResponseEntity<List<SeeTechnicianOrdersResponse>> seeOrderListForGetTechnicianOffer(Principal principal){
        List<Order>orderList=technicianSubServiceService.seeTechnicianOrderList(principal.getName());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelListToSeeTechnicianOrdersResponse(orderList),HttpStatus.FOUND);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PatchMapping("/order-status-to-started")
    public ResponseEntity<OrderChangeStatusResponse> orderStatusToStarted(@Valid @RequestBody OrderChangeStatusRequest request) {
        Order updatedOrder = orderService.changeOrderStatusToStarted(request.orderId());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderChangeStatusResponse(updatedOrder), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PatchMapping("/order-status-to-done")
    public ResponseEntity<OrderChangeStatusResponse> orderStatusToDone(@Valid @RequestBody OrderChangeStatusRequest request) {
        Order updatedOrder = orderService.changeOrderStatusToDone(request.orderId());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderChangeStatusResponse(updatedOrder), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PatchMapping("/pay-order-from-wallet")
    public ResponseEntity<PayOrderFromWalletResponse> payOrderFromWallet(@Valid @RequestBody PayOrderFromWalletRequest request) {
        Order order = orderService.payOrderFromWallet(request.orderId());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToPayOrderFromWalletResponse(order), HttpStatus.OK);
    }

}
