package com.example.demo.dto;

public class PostResponse {

    private Long id;
    private String title;
    private String content;

    private Long userId;
    private String userName;

    public PostResponse(){}

    public PostResponse(Long id, String title, String content, Long userId, String userName){
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.userName = userName;
    }

    //getters / setters
    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public Long getUserId(){
        return userId;
    }
    public String getUserName(){
        return userName;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
}
