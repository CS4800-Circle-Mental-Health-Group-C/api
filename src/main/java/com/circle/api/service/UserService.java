package com.circle.api.service;

import org.springframework.stereotype.Service;

import com.circle.api.model.User;
import com.circle.api.repository.UserRepository;

@Service
public class UserService {

  private UserRepository userRepository;

  UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void createUser(final User user) {
    userRepository.save(user);
  }

  public User getUser(final String id) {
    return userRepository.findById(id).get();
  }
}
