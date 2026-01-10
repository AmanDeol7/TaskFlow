package com.demo.taskflow.user.dto;

import com.demo.taskflow.user.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private long id;
    private String email;
    private String username;
    private Role role;
}

