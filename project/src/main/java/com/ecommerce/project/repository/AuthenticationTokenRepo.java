package com.ecommerce.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.project.model.AuthenticationToken;
import com.ecommerce.project.model.User;

public interface AuthenticationTokenRepo extends JpaRepository<AuthenticationToken, Integer> {

  AuthenticationToken findByUser(User user);
}
