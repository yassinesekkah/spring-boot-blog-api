package com.example.demo.dto;

public class CommentRequest {

    private String content;
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
