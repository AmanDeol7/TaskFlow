package com.demo.taskflow.user;

import com.demo.taskflow.user.dto.UserPasswordUpdateRequest;
import com.demo.taskflow.user.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.net.URI;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponse> list(){
        return userService.list();
    }



    @GetMapping("/me")
    public UserResponse get(){
        return userService.getMe();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        UserResponse user =  userService.getById(id);
        return ResponseEntity.ok(user);

    }

    @PutMapping("/me/password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updatePassword( @Valid @RequestBody UserPasswordUpdateRequest req){

        userService.updatePassword(req);
        return ResponseEntity.ok("Password updated successfully");

    }



}
