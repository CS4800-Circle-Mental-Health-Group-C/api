package com.circle.api.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.circle.api.model.User;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {}
