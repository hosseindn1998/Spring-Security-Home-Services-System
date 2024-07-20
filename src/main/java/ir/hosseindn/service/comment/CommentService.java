package ir.hosseindn.service.comment;

import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Comment;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.enums.OrderStatus;
import ir.hosseindn.repository.comment.CommentRepository;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.technician.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final OrderService orderService;
    private final TechnicianService technicianService;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment addCommentByCustomer(Comment comment, Long orderId) {
        Order order = orderService.findById(orderId);
        if (order.getComment() != null)
            throw new NotValidInformation("this order exist already a comment");
        if (!order.getOrderStatus().equals(OrderStatus.Paid))
            throw new NotValidInformation("this order must be paid an then can comment on it");
        Technician technician = order.getTechnician();
        technician.setTotalScores(technician.getTotalScores() + comment.getRate());
        technician.setCountScores(technician.getCountScores() + 1);
        comment.setTechnician(technician);
        Comment savedComment = save(comment);
        order.setComment(savedComment);
        orderService.save(order);
        List<Comment> comments = technician.getComments();
        comments.add(savedComment);
        technician.setComments(comments);
        technician.setRate((double) technician.getTotalScores() / technician.getCountScores());
        technicianService.update(technician);
        return savedComment;
    }

    public List<Comment> findAllByTechnicianUsername(String email) {
        List<Comment> commentList = commentRepository.findAllByTechnicianEmail(email);
        if (commentList.isEmpty())
            throw new NotFoundException("No Comment Found");
        return commentList;
    }
}
