package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10, max = 5000, message = "Content must be between 10 and 5000 characters")
    private String content;

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Category Id is required")
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
