package com.example.demo.dto;

public class PostRequest {

    private String title;
    private String content;
    private Long userId;
    private Long categoryId;

    public PostRequest(String title, String content, Long userId, Long categoryId){
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    //getters / setters
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public Long getUserId(){
        return userId;
    }
    public Long getCategoryId() { return categoryId; }

    public void setTitle(String title){
        this.title = title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}
