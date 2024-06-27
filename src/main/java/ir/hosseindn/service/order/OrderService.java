package ir.hosseindn.service.order;

import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.OrderStatus;
import ir.hosseindn.model.SubService;
import ir.hosseindn.repository.order.OrderRepository;
import ir.hosseindn.service.offer.OfferService;
import ir.hosseindn.service.subservice.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final SubServiceService subServiceService;


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
    public Order chooseOffer(Long id, Offer offer){
        Order order=orderRepository.findById(id).orElseThrow(
                ()->new NotFoundException(String.format("Not found order id %s",id))
        );
        if(order.getChoosedOffer()!=null)
            throw new NotValidInformation("choose offer already exists for this order");
        orderRepository.chooseOffer(id,offer);
        orderRepository.changeOrderStatus(id,OrderStatus.WAIT_FOR_CHOOSE_TECHNICIAN);
        return order;
    }
    public Order changeOrderStatusToStarted(Long id,Offer offer){
        if(LocalDate.now().isBefore(offer.getDateOfOfferToStart()))
            throw new NotValidInformation("start time can't be before in offer's start ");
        Order order= orderRepository.findById(id).orElseThrow(
                ()->new NotFoundException("Order Not found")
        );
        orderRepository.changeOrderStatus(id,OrderStatus.STARTED);
        return order;
    }
    public Order changeOrderStatusToDone(Long id,Offer offer){
        if(LocalDate.now().isBefore(offer.getDateOfOfferToStart()))
            throw new NotValidInformation("start time can't be before in offer's start ");
        Order order= orderRepository.findById(id).orElseThrow(
                ()->new NotFoundException("Order Not found")
        );
        orderRepository.changeOrderStatus(id,OrderStatus.DONE);
        return order;
    }
}
