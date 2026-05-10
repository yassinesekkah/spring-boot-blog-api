package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

    public Map<String, String> login(LoginRequest request){

//        System.out.println(request.getEmail());
//        System.out.println(request.getPassword());

        //get user
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

//        System.out.println(user.getName());

        //check password
        //matches kathashe lpassword dyal request wkadirlo compare m3a lpassword li f db
        boolean isPasswordCorrect = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if(!isPasswordCorrect){
            throw new RuntimeException("invalid email or password");
        }

        //generate jwt token
        String token = jwtService.generateToken(user.getEmail());

        return Map.of("token", token);
    }

}
