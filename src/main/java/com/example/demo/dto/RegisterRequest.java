package com.example.demo.dto;

import jakarta.validation.constraints.*;

public class RegisterRequest {

    @NotBlank(message = "name is required")
    @Size(min = 2, message = "name must be a least 2 characters")
    private String name;


    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18 ")
    private Integer age;

    public RegisterRequest(){}

    public RegisterRequest(String name, String email, String password, Integer age){

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
    public Integer getAge(){
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
    public void setAge(Integer age){
        this.age = age;
    }
}
