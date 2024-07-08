package ir.hosseindn.mapper.comment;

import ir.hosseindn.dto.comment.SaveCommentRequest;
import ir.hosseindn.dto.comment.SaveCommentResponse;
import ir.hosseindn.dto.order.OrderId;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.model.Comment;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Technician;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-08T06:38:51+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentSaveRequestToModel(SaveCommentRequest request) {
        if ( request == null ) {
            return null;
        }

        Comment.CommentBuilder<?, ?> comment = Comment.builder();

        comment.rate( request.rate() );
        comment.description( request.description() );
        comment.order( orderIdToOrder( request.order() ) );

        return comment.build();
    }

    @Override
    public SaveCommentResponse modelToSaveCommentResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        Integer rate = null;
        String description = null;
        OrderId order = null;
        TechnicianId technician = null;

        rate = comment.getRate();
        description = comment.getDescription();
        order = orderToOrderId( comment.getOrder() );
        technician = technicianToTechnicianId( comment.getTechnician() );

        SaveCommentResponse saveCommentResponse = new SaveCommentResponse( rate, description, order, technician );

        return saveCommentResponse;
    }

    protected Order orderIdToOrder(OrderId orderId) {
        if ( orderId == null ) {
            return null;
        }

        Order.OrderBuilder<?, ?> order = Order.builder();

        order.id( orderId.id() );

        return order.build();
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
}
