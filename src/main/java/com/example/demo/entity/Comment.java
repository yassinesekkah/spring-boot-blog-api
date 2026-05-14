package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    private LocalDateTime createdAt;


    public Comment(){}

    public Comment(String content, Post post){
        this.content = content;
        this.post = post;
        this.createdAt = LocalDateTime.now();
    }

    //getters / setters
    public Long getId(){
        return id;
    }
    public String getContent(){
        return content;
    }
    public Post getPost(){
        return post;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setPost(Post post){
        this.post = post;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

}
