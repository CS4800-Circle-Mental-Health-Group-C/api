package com.circle.api.facade;

import org.springframework.stereotype.Component;

import com.circle.api.controller.UserRequest;
import com.circle.api.controller.UserResponse;
import com.circle.api.model.User;
import com.circle.api.service.UserService;

@Component
public class UserFacade {

    private final UserService userService; 

    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public UserResponse findById(String id) {

        User user = userService.findById(id);
        return UserResponse.from(user);
    }

    public UserResponse createUser(User user) {
        User userToCreate = UserRequest.toUser(user);
        User createdUser = userService.createUser(userToCreate);
        return UserResponse.from(createdUser);
    }
    
}
