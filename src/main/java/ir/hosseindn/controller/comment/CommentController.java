package ir.hosseindn.controller.comment;

import ir.hosseindn.dto.comment.SaveCommentRequest;
import ir.hosseindn.dto.comment.SaveCommentResponse;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.mapper.comment.CommentMapper;
import ir.hosseindn.model.Comment;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.OrderStatus;
import ir.hosseindn.model.Technician;
import ir.hosseindn.service.comment.CommentService;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.technician.TechnicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final OrderService orderService;
    private final TechnicianService technicianService;

    @PostMapping("/add-comment")
    public ResponseEntity<SaveCommentResponse> customerRegister(@Valid @RequestBody SaveCommentRequest request) {
        Comment mappedComment = CommentMapper.INSTANCE.commentSaveRequestToModel(request);
        Order order = orderService.findById(mappedComment.getOrder().getId());
        if(order.getComment()!=null)
            throw new NotValidInformation("this order exist already a comment");
        if(!order.getOrderStatus().equals(OrderStatus.Paid))
            throw new NotValidInformation("this order muse be paid an then can comment on it");
        Technician technician = order.getChoosedOffer().getTechnician();
        technician.setTotalScores(technician.getTotalScores() + mappedComment.getRate());
        technician.setCountScores(technician.getCountScores() + 1);
        mappedComment.setTechnician(technician);
        Comment savedComment = commentService.save(mappedComment);
        order.setComment(savedComment);
        orderService.save(order);
        List<Comment> comments = technician.getComments();
        comments.add(savedComment);
        technician.setComments(comments);
        technician.setRate((double) technician.getTotalScores()/technician.getCountScores());
        technicianService.update(technician);
        return new ResponseEntity<>(CommentMapper.INSTANCE.modelToSaveCommentResponse(savedComment), HttpStatus.CREATED);
    }
}
