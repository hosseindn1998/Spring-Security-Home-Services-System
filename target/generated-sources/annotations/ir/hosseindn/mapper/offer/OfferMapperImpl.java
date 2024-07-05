package ir.hosseindn.mapper.offer;

import ir.hosseindn.dto.offer.OfferFindByOrderRequest;
import ir.hosseindn.dto.offer.OfferFindByOrderResponse;
import ir.hosseindn.dto.offer.OfferId;
import ir.hosseindn.dto.offer.OfferSaveRequestWithoutFKs;
import ir.hosseindn.dto.offer.OfferSaveResponse;
import ir.hosseindn.dto.order.OrderId;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.dto.technician.TechnicianSaveResponse;
import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Roles;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.TechnicianStatus;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-04T23:12:28+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class OfferMapperImpl implements OfferMapper {

    @Override
    public Offer offerSaveRequestToModel(OfferSaveRequestWithoutFKs request) {
        if ( request == null ) {
            return null;
        }

        Offer.OfferBuilder<?, ?> offer = Offer.builder();

        offer.dateOfOfferToStart( request.dateOfOfferToStart() );
        offer.suggestPrice( request.suggestPrice() );
        offer.dateOfOfferToDone( request.dateOfOfferToDone() );
        offer.isAccepted( request.isAccepted() );

        return offer.build();
    }

    @Override
    public OfferSaveResponse modelToOfferSaveResponse(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        Long id = null;
        OrderId odrer = null;
        LocalDateTime dateOfOfferToStart = null;
        Long suggestPrice = null;
        LocalDateTime dateOfOfferToDone = null;
        TechnicianId technician = null;
        Boolean isAccepted = null;

        id = offer.getId();
        odrer = orderToOrderId( offer.getOdrer() );
        dateOfOfferToStart = offer.getDateOfOfferToStart();
        suggestPrice = offer.getSuggestPrice();
        dateOfOfferToDone = offer.getDateOfOfferToDone();
        technician = technicianToTechnicianId( offer.getTechnician() );
        isAccepted = offer.getIsAccepted();

        OfferSaveResponse offerSaveResponse = new OfferSaveResponse( id, odrer, dateOfOfferToStart, suggestPrice, dateOfOfferToDone, technician, isAccepted );

        return offerSaveResponse;
    }

    @Override
    public Offer offerFindByOrderRequestToModel(OfferFindByOrderRequest request) {
        if ( request == null ) {
            return null;
        }

        Offer.OfferBuilder<?, ?> offer = Offer.builder();

        offer.odrer( orderIdToOrder( request.odrer() ) );

        return offer.build();
    }

    @Override
    public OfferFindByOrderResponse modelToOfferFindByOrderResponse(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        LocalDateTime dateOfOfferToStart = null;
        Long suggestPrice = null;
        LocalDateTime dateOfOfferToDone = null;
        TechnicianSaveResponse technician = null;
        Boolean isAccepted = null;

        dateOfOfferToStart = offer.getDateOfOfferToStart();
        suggestPrice = offer.getSuggestPrice();
        dateOfOfferToDone = offer.getDateOfOfferToDone();
        technician = technicianToTechnicianSaveResponse( offer.getTechnician() );
        isAccepted = offer.getIsAccepted();

        OfferFindByOrderResponse offerFindByOrderResponse = new OfferFindByOrderResponse( dateOfOfferToStart, suggestPrice, dateOfOfferToDone, technician, isAccepted );

        return offerFindByOrderResponse;
    }

    @Override
    public Offer offerIdToModel(OfferId offerId) {
        if ( offerId == null ) {
            return null;
        }

        Offer.OfferBuilder<?, ?> offer = Offer.builder();

        offer.id( offerId.id() );

        return offer.build();
    }

    protected OrderId orderToOrderId(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;

        id = order.getId();

        OrderId orderId = new OrderId( id );

        return orderId;
    }

    protected TechnicianId technicianToTechnicianId(Technician technician) {
        if ( technician == null ) {
            return null;
        }

        Long id = null;

        id = technician.getId();

        TechnicianId technicianId = new TechnicianId( id );

        return technicianId;
    }

    protected Order orderIdToOrder(OrderId orderId) {
        if ( orderId == null ) {
            return null;
        }

        Order.OrderBuilder<?, ?> order = Order.builder();

        order.id( orderId.id() );

        return order.build();
    }

    protected TechnicianSaveResponse technicianToTechnicianSaveResponse(Technician technician) {
        if ( technician == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String lastName = null;
        String nationalCode = null;
        String email = null;
        LocalDateTime registeredDate = null;
        Double rate = null;
        Integer totalScores = null;
        Long countScores = null;
        TechnicianStatus technicianStatus = null;
        Boolean isActive = null;
        Roles role = null;

        id = technician.getId();
        firstName = technician.getFirstName();
        lastName = technician.getLastName();
        nationalCode = technician.getNationalCode();
        email = technician.getEmail();
        registeredDate = technician.getRegisteredDate();
        rate = technician.getRate();
        totalScores = technician.getTotalScores();
        if ( technician.getCountScores() != null ) {
            countScores = technician.getCountScores().longValue();
        }
        technicianStatus = technician.getTechnicianStatus();
        isActive = technician.getIsActive();
        role = technician.getRole();

        TechnicianSaveResponse technicianSaveResponse = new TechnicianSaveResponse( id, firstName, lastName, nationalCode, email, registeredDate, rate, totalScores, countScores, technicianStatus, isActive, role );

        return technicianSaveResponse;
    }
}
