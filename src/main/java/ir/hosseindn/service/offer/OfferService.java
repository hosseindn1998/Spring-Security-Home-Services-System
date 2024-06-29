package ir.hosseindn.service.offer;

import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.OrderStatus;
import ir.hosseindn.repository.offer.OfferRepository;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.subservice.SubServiceService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final OrderService orderService;
    public Offer save(Offer offer){
        Order foundedOrder = orderService.findById(offer.getOdrer().getId());
        if(!orderService.isOpenToGetOffer(offer.getOdrer().getId()))
            throw new NotValidInformation("this order is close to get offer");
        if(offer.getSuggestPrice()<foundedOrder.getSubservice().getBasePrice())
            throw new NotValidInformation("Technician suggest-price can't be lower than base-price");
        if(foundedOrder.getOffers()==null){
            foundedOrder.setOrderStatus(OrderStatus.WAIT_FOR_CHOOSE_TECHNICIAN);
            offer.setOdrer(foundedOrder);
            orderService.save(foundedOrder);
        }
        offer.setIsAccepted(false);
        return offerRepository.save(offer);
    }
    public List<Offer>findAllByOrder(Long order){
        Order foundOrder=orderService.findById(order);
        List<Offer> offerList = offerRepository.customQuery(foundOrder.getId());
        if(offerList.isEmpty())
            throw new NotFoundException(String.format("No offers for order id=%s found",order));
        return offerList;
    }

}
