package com.demo.taskflow.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {
    public static String getEmail(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if( authentication == null || !authentication.isAuthenticated() ){
            throw new AccessDeniedException("Unauthorized.");
        }
        return authentication.getName(); // it will return email

    }
}
