package ir.hosseindn.mapper.order;

import ir.hosseindn.dto.customer.CustomerId;
import ir.hosseindn.dto.offer.OfferId;
import ir.hosseindn.dto.offer.OfferSaveResponse;
import ir.hosseindn.dto.order.OrderChangeStatusRequest;
import ir.hosseindn.dto.order.OrderChangeStatusResponse;
import ir.hosseindn.dto.order.OrderChooseOfferRequest;
import ir.hosseindn.dto.order.OrderChooseOfferResponse;
import ir.hosseindn.dto.order.OrderId;
import ir.hosseindn.dto.order.OrderSaveRequest;
import ir.hosseindn.dto.order.OrderSaveResponse;
import ir.hosseindn.dto.order.OrderSearchItemResponse;
import ir.hosseindn.dto.order.OrderSearchItemsRequest;
import ir.hosseindn.dto.order.PayOrderFromPaymentRequest;
import ir.hosseindn.dto.order.PayOrderFromPaymentResponse;
import ir.hosseindn.dto.order.PayOrderFromWalletRequest;
import ir.hosseindn.dto.order.PayOrderFromWalletResponse;
import ir.hosseindn.dto.order.SeeCustomerOrdersResponse;
import ir.hosseindn.dto.order.SeeTechnicianOrdersResponse;
import ir.hosseindn.dto.subservice.SubServiceId;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.model.Customer;
import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.SubService;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.enums.OrderStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-21T15:20:12+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class OrderMapperImpl implements OrderMapper {

    private final DatatypeFactory datatypeFactory;

    public OrderMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public Order orderSaveRequestToModel(OrderSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        Order.OrderBuilder<?, ?> order = Order.builder();

        order.suggestedPrice( request.suggestedPrice() );
        order.description( request.description() );
        order.dateForDo( request.dateForDo() );
        order.address( request.address() );

        return order.build();
    }

    @Override
    public OrderSaveResponse modelToOrderSaveResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        SubServiceId subservice = null;
        CustomerId customer = null;
        Long suggestedPrice = null;
        String description = null;
        LocalDateTime dateForDo = null;
        String address = null;

        subservice = subServiceToSubServiceId( order.getSubservice() );
        customer = customerToCustomerId( order.getCustomer() );
        suggestedPrice = order.getSuggestedPrice();
        description = order.getDescription();
        dateForDo = order.getDateForDo();
        address = order.getAddress();

        OrderSaveResponse orderSaveResponse = new OrderSaveResponse( subservice, customer, suggestedPrice, description, dateForDo, address );

        return orderSaveResponse;
    }

    @Override
    public Order orderChooseOfferToModel(OrderChooseOfferRequest request) {
        if ( request == null ) {
            return null;
        }

        Order.OrderBuilder<?, ?> order = Order.builder();

        return order.build();
    }

    @Override
    public OrderChooseOfferResponse modelToOrderChooseOfferResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OfferSaveResponse choosedOffer = null;

        choosedOffer = offerToOfferSaveResponse( order.getChoosedOffer() );

        OrderChooseOfferResponse orderChooseOfferResponse = new OrderChooseOfferResponse( choosedOffer );

        return orderChooseOfferResponse;
    }

    @Override
    public Order orderChangeStatusRequestToModel(OrderChangeStatusRequest request) {
        if ( request == null ) {
            return null;
        }

        Order.OrderBuilder<?, ?> order = Order.builder();

        return order.build();
    }

    @Override
    public OrderChangeStatusResponse modelToOrderChangeStatusResponse(Order oder) {
        if ( oder == null ) {
            return null;
        }

        SubServiceId subservice = null;
        CustomerId customer = null;
        Long suggestedPrice = null;
        String description = null;
        LocalDate dateForDo = null;
        OrderStatus orderStatus = null;
        String address = null;
        OfferId choosedOffer = null;

        subservice = subServiceToSubServiceId( oder.getSubservice() );
        customer = customerToCustomerId( oder.getCustomer() );
        suggestedPrice = oder.getSuggestedPrice();
        description = oder.getDescription();
        dateForDo = xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( oder.getDateForDo() ) );
        orderStatus = oder.getOrderStatus();
        address = oder.getAddress();
        choosedOffer = offerToOfferId( oder.getChoosedOffer() );

        OrderChangeStatusResponse orderChangeStatusResponse = new OrderChangeStatusResponse( subservice, customer, suggestedPrice, description, dateForDo, orderStatus, address, choosedOffer );

        return orderChangeStatusResponse;
    }

    @Override
    public Order orderIdToModel(Long orderId) {
        if ( orderId == null ) {
            return null;
        }

        Order.OrderBuilder<?, ?> order = Order.builder();

        return order.build();
    }

    @Override
    public Order payOrderFromWalletRequestToModel(PayOrderFromWalletRequest request) {
        if ( request == null ) {
            return null;
        }

        Order.OrderBuilder<?, ?> order = Order.builder();

        return order.build();
    }

    @Override
    public PayOrderFromWalletResponse modelToPayOrderFromWalletResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        SubServiceId subservice = null;
        CustomerId customer = null;
        Long suggestedPrice = null;
        String description = null;
        LocalDateTime dateForDo = null;
        OrderStatus orderStatus = null;
        String address = null;
        OfferId choosedOffer = null;

        subservice = subServiceToSubServiceId( order.getSubservice() );
        customer = customerToCustomerId( order.getCustomer() );
        suggestedPrice = order.getSuggestedPrice();
        description = order.getDescription();
        dateForDo = order.getDateForDo();
        orderStatus = order.getOrderStatus();
        address = order.getAddress();
        choosedOffer = offerToOfferId( order.getChoosedOffer() );

        PayOrderFromWalletResponse payOrderFromWalletResponse = new PayOrderFromWalletResponse( subservice, customer, suggestedPrice, description, dateForDo, orderStatus, address, choosedOffer );

        return payOrderFromWalletResponse;
    }

    @Override
    public Order payOrderFromPaymentRequestToModel(PayOrderFromPaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        Order.OrderBuilder<?, ?> order = Order.builder();

        return order.build();
    }

    @Override
    public PayOrderFromPaymentResponse modelToPayOrderFromPaymentResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        SubServiceId subservice = null;
        CustomerId customer = null;
        Long suggestedPrice = null;
        String description = null;
        LocalDateTime dateForDo = null;
        OrderStatus orderStatus = null;
        String address = null;
        OfferId choosedOffer = null;

        subservice = subServiceToSubServiceId( order.getSubservice() );
        customer = customerToCustomerId( order.getCustomer() );
        suggestedPrice = order.getSuggestedPrice();
        description = order.getDescription();
        dateForDo = order.getDateForDo();
        orderStatus = order.getOrderStatus();
        address = order.getAddress();
        choosedOffer = offerToOfferId( order.getChoosedOffer() );

        PayOrderFromPaymentResponse payOrderFromPaymentResponse = new PayOrderFromPaymentResponse( subservice, customer, suggestedPrice, description, dateForDo, orderStatus, address, choosedOffer );

        return payOrderFromPaymentResponse;
    }

    @Override
    public List<SeeCustomerOrdersResponse> modelListToSeeCustomerOrdersResponse(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<SeeCustomerOrdersResponse> list = new ArrayList<SeeCustomerOrdersResponse>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( orderToSeeCustomerOrdersResponse( order ) );
        }

        return list;
    }

    @Override
    public List<SeeTechnicianOrdersResponse> modelListToSeeTechnicianOrdersResponse(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<SeeTechnicianOrdersResponse> list = new ArrayList<SeeTechnicianOrdersResponse>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( orderToSeeTechnicianOrdersResponse( order ) );
        }

        return list;
    }

    @Override
    public List<OrderSaveResponse> modelListToOrderSaveResponseList(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<OrderSaveResponse> list = new ArrayList<OrderSaveResponse>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( modelToOrderSaveResponse( order ) );
        }

        return list;
    }

    @Override
    public Order orderSearchItemToModel(OrderSearchItemsRequest request) {
        if ( request == null ) {
            return null;
        }

        Order.OrderBuilder<?, ?> order = Order.builder();

        order.id( request.id() );
        order.dateForDo( request.dateForDo() );
        order.orderStatus( request.orderStatus() );

        return order.build();
    }

    @Override
    public List<OrderSearchItemResponse> modelToOrderSearchItemResponse(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<OrderSearchItemResponse> list = new ArrayList<OrderSearchItemResponse>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( orderToOrderSearchItemResponse( order ) );
        }

        return list;
    }

    private XMLGregorianCalendar localDateTimeToXmlGregorianCalendar( LocalDateTime localDateTime ) {
        if ( localDateTime == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendar(
            localDateTime.getYear(),
            localDateTime.getMonthValue(),
            localDateTime.getDayOfMonth(),
            localDateTime.getHour(),
            localDateTime.getMinute(),
            localDateTime.getSecond(),
            localDateTime.get( ChronoField.MILLI_OF_SECOND ),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDate xmlGregorianCalendarToLocalDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return LocalDate.of( xcal.getYear(), xcal.getMonth(), xcal.getDay() );
    }

    protected SubServiceId subServiceToSubServiceId(SubService subService) {
        if ( subService == null ) {
            return null;
        }

        Long id = null;

        id = subService.getId();

        SubServiceId subServiceId = new SubServiceId( id );

        return subServiceId;
    }

    protected CustomerId customerToCustomerId(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Long id = null;

        id = customer.getId();

        CustomerId customerId = new CustomerId( id );

        return customerId;
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

    protected OfferSaveResponse offerToOfferSaveResponse(Offer offer) {
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

    protected OfferId offerToOfferId(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        Long id = null;

        id = offer.getId();

        OfferId offerId = new OfferId( id );

        return offerId;
    }

    protected SeeCustomerOrdersResponse orderToSeeCustomerOrdersResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        SubServiceId subservice = null;
        CustomerId customer = null;
        Long suggestedPrice = null;
        String description = null;
        LocalDateTime dateForDo = null;
        String address = null;

        id = order.getId();
        subservice = subServiceToSubServiceId( order.getSubservice() );
        customer = customerToCustomerId( order.getCustomer() );
        suggestedPrice = order.getSuggestedPrice();
        description = order.getDescription();
        dateForDo = order.getDateForDo();
        address = order.getAddress();

        SeeCustomerOrdersResponse seeCustomerOrdersResponse = new SeeCustomerOrdersResponse( id, subservice, customer, suggestedPrice, description, dateForDo, address );

        return seeCustomerOrdersResponse;
    }

    protected SeeTechnicianOrdersResponse orderToSeeTechnicianOrdersResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        SubServiceId subservice = null;
        CustomerId customer = null;
        Long suggestedPrice = null;
        String description = null;
        LocalDateTime dateForDo = null;
        String address = null;

        id = order.getId();
        subservice = subServiceToSubServiceId( order.getSubservice() );
        customer = customerToCustomerId( order.getCustomer() );
        suggestedPrice = order.getSuggestedPrice();
        description = order.getDescription();
        dateForDo = order.getDateForDo();
        address = order.getAddress();

        SeeTechnicianOrdersResponse seeTechnicianOrdersResponse = new SeeTechnicianOrdersResponse( id, subservice, customer, suggestedPrice, description, dateForDo, address );

        return seeTechnicianOrdersResponse;
    }

    protected OrderSearchItemResponse orderToOrderSearchItemResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        SubServiceId subservice = null;
        CustomerId customer = null;
        Long suggestedPrice = null;
        String description = null;
        LocalDateTime creationDate = null;
        LocalDateTime dateForDo = null;
        String address = null;
        OrderStatus orderStatus = null;

        id = order.getId();
        subservice = subServiceToSubServiceId( order.getSubservice() );
        customer = customerToCustomerId( order.getCustomer() );
        suggestedPrice = order.getSuggestedPrice();
        description = order.getDescription();
        creationDate = order.getCreationDate();
        dateForDo = order.getDateForDo();
        address = order.getAddress();
        orderStatus = order.getOrderStatus();

        OrderSearchItemResponse orderSearchItemResponse = new OrderSearchItemResponse( id, subservice, customer, suggestedPrice, description, creationDate, dateForDo, address, orderStatus );

        return orderSearchItemResponse;
    }
}
