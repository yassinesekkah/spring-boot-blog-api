package com.example.demo.mapper;

import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        if (user == null) return null;
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }

    public List<UserResponse> toResponseList(List<User> users) {
        if (users == null || users.isEmpty()) return List.of();
        return users.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
