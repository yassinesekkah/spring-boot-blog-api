package com.example.demo.dto;

import java.time.LocalDateTime;

public class CategoryResponse {

    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public CategoryResponse(){

    }
    public CategoryResponse(Long id, String name, LocalDateTime createdAt){
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    //getters / setters
    public Long getId(){ return id; }
    public String getName(){ return  name; }
    public LocalDateTime getCreatedAt(){ return createdAt; }

    public void setId(Long id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt; }

    @Override
    public String toString(){
        return "CategoryResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
