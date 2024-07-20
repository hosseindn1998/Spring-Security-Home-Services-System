package ir.hosseindn.mapper.comment;

import ir.hosseindn.dto.comment.SaveCommentRequest;
import ir.hosseindn.dto.comment.SaveCommentResponse;
import ir.hosseindn.dto.comment.ShowCommentResponse;
import ir.hosseindn.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment commentSaveRequestToModel(SaveCommentRequest request);

    SaveCommentResponse modelToSaveCommentResponse(Comment comment);
    List<ShowCommentResponse> modelListToShowCommentResponseList(List<Comment> commentList);
}
