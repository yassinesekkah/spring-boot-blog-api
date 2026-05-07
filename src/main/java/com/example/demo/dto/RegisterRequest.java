package com.example.demo.dto;

import jakarta.validation.constraints.*;

public class RegisterRequest {

    @NotBlank
    @Size(min = 2)
    private String name;

    @Email
    @NotBlank
    private String email;

    @Size(min = 8)
    private String password;

    @Min(18)
    private int age;

    public RegisterRequest(){}

    public RegisterRequest(String name, String email, String password, int age){

        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    //getters / setters
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public int getAge(){
        return age;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setAge(int age){
        this.age = age;
    }
}
