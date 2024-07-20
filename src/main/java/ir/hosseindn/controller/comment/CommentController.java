package ir.hosseindn.controller.comment;

import ir.hosseindn.dto.comment.SaveCommentRequest;
import ir.hosseindn.dto.comment.SaveCommentResponse;
import ir.hosseindn.dto.comment.ShowCommentResponse;
import ir.hosseindn.mapper.comment.CommentMapper;
import ir.hosseindn.model.Comment;
import ir.hosseindn.service.comment.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class CommentController {
    private final CommentService commentService;


    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/add-comment")
    public ResponseEntity<SaveCommentResponse> addComment(@Valid @RequestBody SaveCommentRequest request) {
        Comment mappedComment = CommentMapper.INSTANCE.commentSaveRequestToModel(request);
        Comment savedComment = commentService.addCommentByCustomer(mappedComment, request.orderId());
        return new ResponseEntity<>(CommentMapper.INSTANCE.modelToSaveCommentResponse(savedComment), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TECHNICIAN')")
    @GetMapping("/show-comments")
    public ResponseEntity<List<ShowCommentResponse>> showComments(Principal principal) {
        List<Comment> commentList = commentService.findAllByTechnicianUsername(principal.getName());
        return new ResponseEntity<>(CommentMapper.INSTANCE.modelListToShowCommentResponseList(commentList), HttpStatus.CREATED);
    }


}
