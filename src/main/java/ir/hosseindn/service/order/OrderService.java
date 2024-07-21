package ir.hosseindn.service.order;

import ir.hosseindn.dto.order.OrderSearchItemsRequest;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.*;
import ir.hosseindn.model.enums.OrderStatus;
import ir.hosseindn.repository.order.OrderRepository;
import ir.hosseindn.service.customer.CustomerService;
import ir.hosseindn.service.offer.OfferService;
import ir.hosseindn.service.paymenttransaction.PaymentTransactionService;
import ir.hosseindn.service.subservice.SubServiceService;
import ir.hosseindn.service.technician.TechnicianService;
import ir.hosseindn.service.wallet.WalletService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    private final TechnicianService technicianService;
    private final WalletService walletService;
    private final PaymentTransactionService paymentTransactionService;
    private final CustomerService customerService;
    private final SubServiceService subServiceService;
    @Autowired
    @Lazy
    private OfferService offerService;
    @Autowired
    EntityManager entityManager;


    public Order save(Order order) {
        order.setOrderStatus(OrderStatus.WAIT_FOR_TECHNICIAN_OFFER);
        order.setCreationDate(LocalDateTime.now());
        order.setHasComment(false);
        order.setMainServiceName(order.getSubservice().getMainService().getName());
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Order with id %s not found", id)
                ));
    }

    public void hasComment(Long id) {
        orderRepository.orderHasComment(id);
    }

    public Order addOrderByCustomer(Order order, String email, Long subServiceId) {
        order.setCustomer(customerService.findByUsername(email));
        order.setSubservice(subServiceService.findById(subServiceId));
        return save(order);
    }

    public Boolean isOpenToGetOffer(Long id) {
        return orderRepository.isOpenToGetOffer(id).isPresent();
    }

    public Order chooseOffer(Long orderId, Long offerId) {
        Offer foundedOffer = offerService.findById(offerId);
        Order foundedOrder = findById(orderId);
        if (foundedOffer.getDateOfOfferToStart().isAfter(foundedOffer.getDateOfOfferToDone()))
            throw new NotValidInformation("Start date can't after done date");
        if (foundedOrder.getDateForDo().isBefore(foundedOffer.getDateOfOfferToDone()))
            throw new NotValidInformation("deadline for this Order was in " + foundedOrder.getDateForDo()
                    + "and your offer's done date is " + foundedOffer.getDateOfOfferToDone());
        foundedOrder.setChoosedOffer(foundedOffer);
        foundedOrder.setOrderStatus(OrderStatus.WAIT_FOR_COME_TECHNICIAN);
        foundedOrder.setTechnician(foundedOffer.getTechnician());
        offerService.nowIsAccepted(foundedOffer.getId());
        return orderRepository.save(foundedOrder);
    }

    public List<Order> seeAllByCustomer(String email) {
        List<Order> orderList = orderRepository.seeAllByCustomerEmail(email);
        if (orderList.isEmpty())
            throw new NotFoundException("Any order not found");
        return orderList;
    }

    public List<Order> seeAllBySubService(String subService) {
        List<Order> orderList = orderRepository.seeAllBySubService(subService);
        if (orderList.isEmpty())
            throw new NotFoundException("Any order not found");
        return orderList;
    }

    public Order changeOrderStatusToStarted(Long orderId) {
        Order foundedOrder = findById(orderId);
        if (foundedOrder.getChoosedOffer() == null)
            throw new NotFoundException(String.format("for order %s,offer choose not found", orderId));
        if (LocalDateTime.now().isBefore(foundedOrder.getChoosedOffer().getDateOfOfferToStart()))
            throw new NotValidInformation("start time can't be before in offer's start ");
        orderRepository.changeOrderStatus(orderId, OrderStatus.STARTED);
        foundedOrder.setOrderStatus(OrderStatus.STARTED);
        return foundedOrder;
    }

    public Order changeOrderStatusToDone(Long orderId) {
        Order foundedOrder = findById(orderId);
        if (foundedOrder.getOrderStatus() != (OrderStatus.STARTED))
            throw new NotValidInformation("OrderStatus of order that you choose must be started ");
        if (foundedOrder.getChoosedOffer() == null)
            throw new NotFoundException(String.format("for order %s,offer choose not found", orderId));
        orderRepository.changeOrderStatus(foundedOrder.getId(), OrderStatus.DONE);
        foundedOrder.setOrderStatus(OrderStatus.DONE);
        return updateScoresIfDoneLate(foundedOrder);
    }

    public Order updateScoresIfDoneLate(Order order) {
        if (LocalDateTime.now().isAfter(order.getChoosedOffer().getDateOfOfferToDone())) {
            long hours = Duration.between(LocalDateTime.now(), order.getChoosedOffer().getDateOfOfferToDone())
                    .toHours();
            Technician technician = order.getChoosedOffer().getTechnician();
            technicianService.updateScores(technician.getId(), hours);
        }
        return order;
    }

    public Order changeOrderStatusToPaid(Order order) {
        orderRepository.changeOrderStatus(order.getId(), OrderStatus.Paid);
        order.setOrderStatus(OrderStatus.Paid);
        return order;
    }

    public void changeOrderStatusToWaitForChooseTechnician(Long orderId) {
        orderRepository.changeOrderStatus(orderId, OrderStatus.WAIT_FOR_CHOOSE_TECHNICIAN);
    }

    public List<Order> findByCriteria(OrderSearchItemsRequest orderSearchItems) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> orderCriteriaQuery = builder.createQuery(Order.class);
        Root<Order> root = orderCriteriaQuery.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        if (orderSearchItems.customerId() != null) {
            Join<Order, Customer> orderCustomerJoin = root.join("customer", JoinType.INNER);
            predicates.add(builder.equal(orderCustomerJoin.get("id"), orderSearchItems.customerId()));
        }

        if (orderSearchItems.technicianId() != null) {
            Join<Order, Technician> orderTechnicianJoin = root.join("technician", JoinType.INNER);
            predicates.add(builder.equal(orderTechnicianJoin.get("id"), orderSearchItems.technicianId()));
        }

        if (orderSearchItems.id() != null)
            predicates.add(builder.equal(root.get("id"), orderSearchItems.id()));
        if (orderSearchItems.dateForDo() != null)
            predicates.add(builder.lessThanOrEqualTo(root.get("dateForDo"), orderSearchItems.dateForDo()));
        if (orderSearchItems.orderStatus() != null)
            predicates.add(builder.equal(root.get("orderStatus"), orderSearchItems.orderStatus()));

        if (orderSearchItems.subService() != null) {
            Join<Order, SubService> subServiceJoin = root.join("subservice", JoinType.INNER);
            predicates.add(builder.equal(subServiceJoin.get("name"), orderSearchItems.subService()));
        }

        if (orderSearchItems.mainService() != null)
            predicates.add(builder.equal(root.get("mainServiceName"), orderSearchItems.mainService()));

        orderCriteriaQuery.where(builder.and(predicates.toArray(predicates.toArray(new Predicate[]{}))));

        return entityManager.createQuery(orderCriteriaQuery).getResultList();
    }

    public Order payOrderFromWallet(Long orderId) {
        Order order = findById(orderId);
        if (order.getOrderStatus() != OrderStatus.DONE)
            throw new NotValidInformation("Only orders that status = 'done' access to pay");
        walletService.payFromWallet(order.getCustomer().getWallet(), order.getChoosedOffer().getTechnician().getWallet()
                , order.getChoosedOffer().getSuggestPrice());
        return changeOrderStatusToPaid(order);
    }

    public PaymentTransaction payOrderFromPayment(Long orderId) {
        Order order = findById(orderId);
        if (order.getOrderStatus() != OrderStatus.DONE)
            throw new NotValidInformation("Only orders that status = 'done' access to pay");
        Long suggestPrice = order.getChoosedOffer().getSuggestPrice();
        PaymentTransaction paymentTransaction1 = PaymentTransaction.builder()
                .orderId(orderId)
                .suggestPrice(suggestPrice)
                .build();
        return paymentTransactionService.save(paymentTransaction1);
    }


}
