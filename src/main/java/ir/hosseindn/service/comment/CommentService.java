package ir.hosseindn.service.comment;

import ir.hosseindn.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
private final CommentRepository commentRepository;

}
