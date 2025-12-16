package com.demo.taskflow.user;

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

    @PostMapping
    public ResponseEntity<User>create(@RequestBody User user){
        User savedUser = userService.create(user);
        return ResponseEntity.created(URI.create("/api/users/"+savedUser.getId())).body(savedUser);
    }
    @GetMapping
    public List<User> list(){
        return userService.list();
    }
    @GetMapping("/{id}")
    public User get(@PathVariable Long id){
        return userService.get(id);
    }

}
