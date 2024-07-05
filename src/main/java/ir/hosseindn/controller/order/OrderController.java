package ir.hosseindn.controller.order;

import ir.hosseindn.dto.order.*;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.mapper.customer.CustomerMapper;
import ir.hosseindn.mapper.offer.OfferMapper;
import ir.hosseindn.mapper.order.OrderMapper;
import ir.hosseindn.mapper.subservice.SubServiceMapper;
import ir.hosseindn.model.*;
import ir.hosseindn.service.offer.OfferService;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.technician.TechnicianService;
import ir.hosseindn.service.wallet.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final OfferService offerService;
    private final TechnicianService technicianService;
    private final WalletService walletService;


    @PostMapping("/add-order")
    public ResponseEntity<OrderSaveResponse> addOrder(@Valid @RequestBody OrderSaveRequest request) {
        Customer customer = CustomerMapper.INSTANCE.customerIdToModel(request.customerId());
        SubService subService = SubServiceMapper.INSTANCE.subServiceIdToModel(request.subserviceId());
        Order mappedOrder = OrderMapper.INSTANCE.orderSaveRequestWithoutFKsToModel(request.order());
        mappedOrder.setCustomer(customer);
        mappedOrder.setSubservice(subService);
        Order savedOrder = orderService.save(mappedOrder);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderSaveResponse(savedOrder), HttpStatus.CREATED);
    }

    @PatchMapping("/choose-offer")
    public ResponseEntity<OrderChooseOfferResponse> chooseOffer(@Valid @RequestBody OrderChooseOfferRequest request) {
        Order mappedOrder = OrderMapper.INSTANCE.orderIdToModel(request.order());
        Offer mappedOffer = OfferMapper.INSTANCE.offerIdToModel(request.offer());
        Offer foundedOffer = offerService.findById(mappedOffer.getId());
        Order foundedOrder = orderService.findById(mappedOrder.getId());
        if (foundedOffer.getDateOfOfferToStart().isAfter(foundedOffer.getDateOfOfferToDone()))
            throw new NotValidInformation("Start date can't after done date");
        if (foundedOrder.getDateForDo().isBefore(foundedOffer.getDateOfOfferToDone()))
            throw new NotValidInformation("deadline for this Order was in " + foundedOrder.getDateForDo()
                    + "and your offer's done date is " + foundedOffer.getDateOfOfferToDone());
        Order updatedOrder = orderService.chooseOffer(foundedOrder, foundedOffer);
        offerService.nowIsAccepted(request.offer().id());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderChooseOffer(updatedOrder, offerService.findById(request.offer().id())), HttpStatus.OK);
    }

    @PatchMapping("/order-status-to-started")
    public ResponseEntity<OrderChangeStatusResponse> orderStatusToStarted(@Valid @RequestBody OrderChangeStatusRequest request) {
        Order mappedOrder = OrderMapper.INSTANCE.orderIdToModel(request.order());
        Order updatedOrder = orderService.changeOrderStatusToStarted(mappedOrder);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderChangeStatusResponse(updatedOrder), HttpStatus.OK);
    }

    @PatchMapping("/order-status-to-done")
    public ResponseEntity<OrderChangeStatusResponse> orderStatusToDone(@Valid @RequestBody OrderChangeStatusRequest request) {
        Order mappedOrder = OrderMapper.INSTANCE.orderIdToModel(request.order());
        Order updatedOrder = orderService.changeOrderStatusToDone(mappedOrder);
        if (LocalDateTime.now().isAfter(updatedOrder.getChoosedOffer().getDateOfOfferToDone())) {
            long hours = Duration.between(LocalDateTime.now(), updatedOrder.getChoosedOffer().getDateOfOfferToDone())
                    .toHours();
            Technician technician = updatedOrder.getChoosedOffer().getTechnician();
            technicianService.updateScores(technician.getId(), hours);
        }
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderChangeStatusResponse(updatedOrder), HttpStatus.OK);
    }

    @PatchMapping("/pay-order-from-wallet")
    public ResponseEntity<PayOrderFromWalletResponse> payOrderFromWallet(@Valid @RequestBody PayOrderFromWalletRequest request) {
        Order order = orderService.findById(request.order().id());
        if (order.getOrderStatus() != OrderStatus.DONE)
            throw new NotValidInformation("Only orders that status = 'done' access to pay");
        walletService.payFromWallet(order.getCustomer().getWallet(), order.getChoosedOffer().getTechnician().getWallet()
                , order.getChoosedOffer().getSuggestPrice());
        orderService.changeOrderStatusToPaid(order);
        order.setOrderStatus(OrderStatus.Paid);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToPayOrderFromWalletResponse(order), HttpStatus.OK);
    }

    @GetMapping("/pay-order-from-payment")
    public ResponseEntity<PayOrderFromPaymentResponse> payOrderFromPayment(@Valid @RequestBody PayOrderFromPaymentRequest request) {
        Order order = orderService.findById(request.order().id());
        if (order.getOrderStatus() != OrderStatus.DONE)
            throw new NotValidInformation("Only orders that status = 'done' access to pay");
        Long paymentPrice = order.getChoosedOffer().getSuggestPrice();

        orderService.changeOrderStatusToPaid(order);
        order.setOrderStatus(OrderStatus.Paid);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToPayOrderFromPaymentResponse(order), HttpStatus.OK);
    }
}
