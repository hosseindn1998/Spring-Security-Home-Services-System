package ir.hosseindn.service.order;

import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.OrderStatus;
import ir.hosseindn.model.SubService;
import ir.hosseindn.repository.order.OrderRepository;
import ir.hosseindn.service.subservice.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final SubServiceService subServiceService;

    public Order save(Order order) {
        SubService foundedSubService = Optional.ofNullable(subServiceService.findByName(order.getSubservice())).orElseThrow(
                () -> new NotFoundException("SubService not found")
        );
        order.setOrderStatus(OrderStatus.WAIT_FOR_TECHNICIAN_OFFER);
        order.setSubservice(foundedSubService);
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Order with id %s not found", id)
                ));
    }
}
