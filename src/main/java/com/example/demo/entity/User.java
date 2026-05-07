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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private int age;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Post> posts;

    public User(){}  // hada 9alek mohim l JPA

    public User(String name, String email, String password ,int age){
        this.name = name;
        this.email = email;
        this.password = password;
        this.age  = age;
    }

    public Long getId(){ return id; }
    public String getName() {
        return name;
    }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
    public int getAge() {
        return age;
    }

    public void setName(String name){ this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setAge(int age){ this.age = age; }
}
