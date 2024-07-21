package ir.hosseindn.mapper.comment;

import ir.hosseindn.dto.comment.SaveCommentRequest;
import ir.hosseindn.dto.comment.SaveCommentResponse;
import ir.hosseindn.dto.comment.ShowCommentResponse;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.model.Comment;
import ir.hosseindn.model.Technician;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-21T15:20:12+0330",
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

        return comment.build();
    }

    @Override
    public SaveCommentResponse modelToSaveCommentResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        Integer rate = null;
        String description = null;
        TechnicianId technician = null;

        rate = comment.getRate();
        description = comment.getDescription();
        technician = technicianToTechnicianId( comment.getTechnician() );

        SaveCommentResponse saveCommentResponse = new SaveCommentResponse( rate, description, technician );

        return saveCommentResponse;
    }

    @Override
    public List<ShowCommentResponse> modelListToShowCommentResponseList(List<Comment> commentList) {
        if ( commentList == null ) {
            return null;
        }

        List<ShowCommentResponse> list = new ArrayList<ShowCommentResponse>( commentList.size() );
        for ( Comment comment : commentList ) {
            list.add( commentToShowCommentResponse( comment ) );
        }

        return list;
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

    protected ShowCommentResponse commentToShowCommentResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        Long id = null;
        Integer rate = null;
        String description = null;

        id = comment.getId();
        rate = comment.getRate();
        description = comment.getDescription();

        ShowCommentResponse showCommentResponse = new ShowCommentResponse( id, rate, description );

        return showCommentResponse;
    }
}
