package ir.hosseindn.mapper.offer;

import ir.hosseindn.dto.offer.OfferFindByOrderRequest;
import ir.hosseindn.dto.offer.OfferFindByOrderResponse;
import ir.hosseindn.dto.offer.OfferSaveRequest;
import ir.hosseindn.dto.offer.OfferSaveResponse;
import ir.hosseindn.dto.order.OrderId;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Technician;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-21T15:20:12+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class OfferMapperImpl implements OfferMapper {

    @Override
    public Offer offerSaveRequestToModel(OfferSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        Offer.OfferBuilder<?, ?> offer = Offer.builder();

        offer.dateOfOfferToStart( request.dateOfOfferToStart() );
        offer.suggestPrice( request.suggestPrice() );
        offer.dateOfOfferToDone( request.dateOfOfferToDone() );

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
    public List<OfferFindByOrderResponse> modelListToOfferFindByOrderResponseList(List<Offer> offerList) {
        if ( offerList == null ) {
            return null;
        }

        List<OfferFindByOrderResponse> list = new ArrayList<OfferFindByOrderResponse>( offerList.size() );
        for ( Offer offer : offerList ) {
            list.add( offerToOfferFindByOrderResponse( offer ) );
        }

        return list;
    }

    @Override
    public Offer offerIdToModel(Long offerId) {
        if ( offerId == null ) {
            return null;
        }

        Offer.OfferBuilder<?, ?> offer = Offer.builder();

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

    protected OfferFindByOrderResponse offerToOfferFindByOrderResponse(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        LocalDateTime dateOfOfferToStart = null;
        Long suggestPrice = null;
        LocalDateTime dateOfOfferToDone = null;
        TechnicianId technician = null;
        Boolean isAccepted = null;

        dateOfOfferToStart = offer.getDateOfOfferToStart();
        suggestPrice = offer.getSuggestPrice();
        dateOfOfferToDone = offer.getDateOfOfferToDone();
        technician = technicianToTechnicianId( offer.getTechnician() );
        isAccepted = offer.getIsAccepted();

        OfferFindByOrderResponse offerFindByOrderResponse = new OfferFindByOrderResponse( dateOfOfferToStart, suggestPrice, dateOfOfferToDone, technician, isAccepted );

        return offerFindByOrderResponse;
    }
}
