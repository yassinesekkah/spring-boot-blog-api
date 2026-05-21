package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentRequest {

    @NotBlank(message = "Content is required")
    @Size(min = 3, max = 200, message = "comment must be between 3 and 200")
    private String content;

    @NotNull(message = "Post ID is required")
    private Long postId;

    public CommentRequest(){}

    public CommentRequest(String content, Long postId){
        this.content = content;
        this.postId = postId;
    }

    //getter and setters
    public String getContent(){
        return content;
    }
    public Long getPostId(){
        return postId;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setPostId(Long postId){
        this.postId = postId;
    }
}
