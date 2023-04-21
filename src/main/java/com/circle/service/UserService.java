package com.circle.service;

import org.springframework.stereotype.Service;

import com.circle.model.User;
import com.circle.repository.UserRepository;

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
