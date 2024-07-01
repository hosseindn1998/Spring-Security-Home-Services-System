package ir.hosseindn.controller.comment;

import ir.hosseindn.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class CommentController {
    private final CommentService commentService;

}
