package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.CommentRequest;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, CommentMapper commentMapper){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
    }

    public CommentDTO createComment(CommentRequest request){
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Comment comment = commentMapper.toEntity(request, post);
        Comment saved = commentRepository.save(comment);

        return commentMapper.toDTO(saved);
    }

    public List<CommentDTO> getCommentsByPost(Long postId){
        if(!postRepository.existsById(postId)){
            throw new EntityNotFoundException("Post not found");
        }

        return commentRepository.findByPostId(postId)
                .stream()
                .map(commentMapper::toDTO)
                .toList();
    }

    public void deleteComment(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }

}
