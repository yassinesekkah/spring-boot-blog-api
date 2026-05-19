package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDateTime createdAt;

    //JPA lifecycle hook
    /**
     * Had method kat-execute-i AUTOMATIC qbel ma JPA y-saviy l'entity.
     * Ma t-y3yet-ch nta — JPA howa li kay-y3yet-ha.
     */
    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    // les relations
    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Post> posts;

    //constuctors
    public Category(){

    }
    public Category(String name){
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    //getters / setters
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    public List<Post> getPosts(){
        return posts;
    }
    public void setPosts(List<Post> posts){
        this.posts = posts;
    }


}
