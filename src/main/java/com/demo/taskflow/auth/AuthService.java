package com.demo.taskflow.auth;

import com.demo.taskflow.common.ResourceNotFoundException;
import com.demo.taskflow.user.Role;
import com.demo.taskflow.user.User;
import com.demo.taskflow.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthService( UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    public void register(RegisterRequest req){
        if( userRepo.existsByEmail(req.getEmail())){
            throw new RuntimeException("Email already exists");

        }

        User user = User.builder().email(req.getEmail()).username(req.getUsername()).password(passwordEncoder.encode(req.getPassword())).role(Role.USER).build();
        userRepo.save(user);
    }

    public void adminRegister(@Valid @RequestBody RegisterRequest req){
        if( userRepo.existsByEmail(req.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder().email(req.getEmail()).username(req.getUsername()).password(passwordEncoder.encode(req.getPassword())).role(Role.ADMIN).build();
        userRepo.save(user);
    }

    public String login(LoginRequest req){
        User user = userRepo.findByEmail(req.getEmail()).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));

        if( !passwordEncoder.matches(req.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getEmail());


    }




}
