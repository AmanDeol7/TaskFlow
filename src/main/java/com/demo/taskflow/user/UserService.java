package com.demo.taskflow.user;

import com.demo.taskflow.common.ResourceNotFoundException;
import com.demo.taskflow.security.CurrentUser;
import com.demo.taskflow.user.dto.UserResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
 private final UserRepository userRepository;
 public UserService(UserRepository userRepository) {
     this.userRepository = userRepository;
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




}
