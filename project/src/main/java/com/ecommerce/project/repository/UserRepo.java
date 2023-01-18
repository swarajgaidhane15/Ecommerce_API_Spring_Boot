package com.ecommerce.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.project.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

  User findByEmail(String email);
}
