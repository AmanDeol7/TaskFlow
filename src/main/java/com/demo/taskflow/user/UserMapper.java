package com.demo.taskflow.user;

import com.demo.taskflow.user.dto.UserResponse;

public class UserMapper {
    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail()).role(user.getRole()).build();

    }
}
