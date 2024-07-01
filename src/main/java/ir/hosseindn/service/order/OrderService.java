package ir.hosseindn.service.order;

import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.*;
import ir.hosseindn.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;



    public Order save(Order order) {
        order.setOrderStatus(OrderStatus.WAIT_FOR_TECHNICIAN_OFFER);
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Order with id %s not found", id)
                ));
    }
    public Boolean isOpenToGetOffer(Long id){
        return orderRepository.isOpenToGetOffer(id).isPresent();
    }
    public Order chooseOffer(Order order, Offer offer){
        orderRepository.chooseOffer(order.getId(), offer.getId());
        orderRepository.changeOrderStatus(order.getId(),OrderStatus.WAIT_FOR_COME_TECHNICIAN);
        return order;
    }
    public Order changeOrderStatusToStarted(Order order){
        Order foundedOrder=findById(order.getId());
        if(foundedOrder.getChoosedOffer()==null)
            throw new NotFoundException(String.format("for order %s,offer choose not found",order.getId()));
        if(LocalDateTime.now().isBefore(foundedOrder.getChoosedOffer().getDateOfOfferToStart()))
            throw new NotValidInformation("start time can't be before in offer's start ");
        orderRepository.changeOrderStatus(order.getId(),OrderStatus.STARTED);
        foundedOrder.setOrderStatus(OrderStatus.STARTED);
        return foundedOrder;
    }
    public Order changeOrderStatusToDone(Order order){
        Order foundedOrder=findById(order.getId());
        if(foundedOrder.getOrderStatus()!=(OrderStatus.STARTED))
            throw new NotValidInformation("OrderStatus of order that you choose must be started ");
        if(foundedOrder.getChoosedOffer()==null)
            throw new NotFoundException(String.format("for order %s,offer choose not found",order.getId()));
        orderRepository.changeOrderStatus(foundedOrder.getId(),OrderStatus.DONE);
        return foundedOrder;
    }
    public void changeOrderStatusToPaid(Order order){
        orderRepository.changeOrderStatus(order.getId(),OrderStatus.Paid);
    }

}
