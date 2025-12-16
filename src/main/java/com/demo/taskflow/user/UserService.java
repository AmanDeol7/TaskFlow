package com.demo.taskflow.user;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
 private final UserRepository userRepository;
 public UserService(UserRepository userRepository) {
     this.userRepository = userRepository;
 }
 public User create(User user){
     return userRepository.save(user);
 }
 public List<User> list(){
     return userRepository.findAll();
 }
 public User get(Long id){
     return userRepository.findById(id).orElseThrow();
 }


}
