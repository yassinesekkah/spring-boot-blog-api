package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;


    public Post(){}

    public Post(String title, String content, User user, Category category){
        this.title = title;
        this.content = content;
        this.user = user;
        this.category = category;
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
    public User getUser(){
        return user;
    }
    public Category getCategory(){ return category; }
    public List<Comment> getComments(){ return comments; }

    public void setId(Long id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setUser(User user){
        this.user = user;
    }
    public void setCategory(Category category) { this.category = category; }
    public void setComments(List<Comment> comments){ this.comments = comments; }
}
