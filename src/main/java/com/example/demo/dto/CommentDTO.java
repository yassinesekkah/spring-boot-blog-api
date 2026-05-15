package com.example.demo.dto;

import com.example.demo.entity.Post;

import java.time.LocalDateTime;

public class CommentDTO {

    private Long id;
    private String content;
    private Long postId;
    private LocalDateTime createdAt;

    public CommentDTO(){}

    public CommentDTO(Long id, String content, Long postId, LocalDateTime createdAt){
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.createdAt = createdAt;
    }

    //getters and setters
    public Long getId() {
        return id;
    }
    public String getContent(){
        return content;
    }
    public Long getPostId(){
        return postId;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setContent(String content) { this.content = content; }
    public void setPostId(Long postId) { this.postId = postId; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
