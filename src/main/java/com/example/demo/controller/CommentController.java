package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.CommentRequest;
import com.example.demo.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public CommentDTO createComment(@RequestBody CommentRequest request){
        return commentService.createComment(request);
    }

    @GetMapping("/post/{postId}")
    public List<CommentDTO> getCommentsByPost(@PathVariable Long postId){
        return commentService.getCommentsByPost(postId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
