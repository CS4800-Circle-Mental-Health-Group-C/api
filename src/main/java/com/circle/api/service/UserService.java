package com.circle.api.service;

import org.springframework.stereotype.Service;

import com.circle.api.model.User;
import com.circle.api.repository.UserRepository;

@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User findById(String id) {
    return userRepository
        .findByUserId(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public User createUser(User user) {
    user.setKey(user.getUserId());
    return userRepository.createUser(user);
  }
}
