package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request){

        //check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if(existingUser.isPresent()){
            throw new RuntimeException("Email already exists");
        }

        //hash password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        //create user
        User user = new User(
                request.getName(),
                request.getEmail(),
                hashedPassword,
                request.getAge()
        );

        //save
        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequest request){

        //get user
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));


        //check password
        boolean isPasswordCorrect = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if(!isPasswordCorrect){
            throw new RuntimeException("invalid email or password");
        }

        return "Login successful";
    }

}
