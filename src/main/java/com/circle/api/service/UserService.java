package com.circle.api.service;

import com.circle.api.model.User;
import org.springframework.stereotype.Service;
import com.circle.api.repository.UserRepository;

@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User findById(String id) {
    return userRepository.findByUserId(id);
  }

  public User createUser(User user) {
    user.setKey(user.getUserId());
    return userRepository.createUser(user);
  }
}
