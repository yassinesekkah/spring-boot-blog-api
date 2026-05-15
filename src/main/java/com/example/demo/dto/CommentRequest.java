package com.example.demo.dto;

public class CommentRequest {

    private Long id;
    private String content;

    public CommentRequest(){}

    public CommentRequest(Long id, String content){
        this.id = id;
        this.content = content;
    }

    //getter and setters
    public Long getId(){
        return id;
    }
    public String getContent(){
        return content;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setContent(String id){
        this.content = content;
    }
}
