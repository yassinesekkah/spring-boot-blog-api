package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int age;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Post> posts;

    public User(){}  // hada 9alek mohim l JPA

    public User(String name, int age){
        this.name = name;
        this.age  = age;
    }

    public Long getId(){ return id; }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public void setName(String name){ this.name = name; }
    public void setAge(int age){ this.age = age; }
}
