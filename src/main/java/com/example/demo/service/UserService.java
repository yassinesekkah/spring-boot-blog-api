package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
         userRepository.save(user);
         return user;
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, User newUser){

        //appel
        User user = getUserById(id);

        user.setName(newUser.getName());
        user.setAge(newUser.getAge());

        return userRepository.save(user);
    }

    public void deleteUser(Long id){

        User user = getUserById(id);

         userRepository.delete(user);
    }
}
