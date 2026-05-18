package com.example.demo.mapper;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.CommentRequest;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    //man entity l dto (kayerja3 lel client)
    public CommentDTO toDTO(Comment comment){
        if(comment == null){
            return null;
        }
        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getPost() != null ? comment.getPost().getId() : null,
                comment.getCreatedAt()
        );
    }

    //man request dto lel entity (kayji mn leclien)
    public Comment toEntity(CommentRequest request, Post post){
        if(request == null){
            return null;
        }
        return new Comment(request.getContent(), post);
    }

}
