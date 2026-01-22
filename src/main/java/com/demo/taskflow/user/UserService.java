package com.demo.taskflow.user;

import com.demo.taskflow.common.ResourceNotFoundException;
import com.demo.taskflow.security.CurrentUser;
import com.demo.taskflow.user.dto.UserPasswordUpdateRequest;
import com.demo.taskflow.user.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
 private final UserRepository userRepository;
 private final PasswordEncoder passwordEncoder;
 public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
     this.userRepository = userRepository;
     this.passwordEncoder = passwordEncoder;
 }

 public List<UserResponse> list(){
      List<User> users = userRepository.findAll();
      return users.stream().map(UserMapper::toUserResponse).toList();

 }


 public UserResponse getMe() {
     User user = userRepository.findByEmail(CurrentUser.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

     return UserMapper.toUserResponse(user);
 }
 public UserResponse getById(Long id){
     User user=  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

     return UserMapper.toUserResponse(user);

 }

 public void updatePassword(UserPasswordUpdateRequest req){
     User user = userRepository.findByEmail(CurrentUser.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

     if( !passwordEncoder.matches(req.getOldPassword(),user.getPassword())){
         throw new IllegalArgumentException("Current password is incorrect");

     }
     if (req.getOldPassword().equals(req.getNewPassword())) {
         throw new IllegalArgumentException("New password must be different from old password");
     }

     String newPassword = passwordEncoder.encode((req.getNewPassword()));
     user.setPassword(newPassword);
     userRepository.save(user);
     return;

 }




}
