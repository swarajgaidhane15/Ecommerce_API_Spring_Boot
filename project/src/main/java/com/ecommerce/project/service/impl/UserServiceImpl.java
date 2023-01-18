package com.ecommerce.project.service.impl;

import jakarta.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import com.ecommerce.project.dto.auth.ResponseDto;
import com.ecommerce.project.dto.auth.SignInDto;
import com.ecommerce.project.dto.auth.SignInResponseDto;
import com.ecommerce.project.dto.auth.SignupDto;
import com.ecommerce.project.exceptions.AuthenticationFailureException;
import com.ecommerce.project.exceptions.CustomException;
import com.ecommerce.project.model.AuthenticationToken;
import com.ecommerce.project.model.User;
import com.ecommerce.project.repository.UserRepo;
import com.ecommerce.project.service.AuthenticationTokenService;
import com.ecommerce.project.service.UserService;

public class UserServiceImpl implements UserService {
  private UserRepo userRepo;

  private AuthenticationTokenService tokenService;

  private String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(password.getBytes());

    byte[] digest = md.digest();

    String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();

    return hash;
  }

  @Override
  public ResponseDto signup(SignupDto body) {
    // Check if user exists
    if (Objects.nonNull(userRepo.findByEmail(body.getEmail()))) {
      throw new CustomException("User already exists");
    }

    // Hash the password
    String encryptedPassword;

    try {
      encryptedPassword = hashPassword(body.getPassword());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new CustomException(e.getMessage());
    }

    // Save the user
    User user = new User(body.getName(), body.getEmail(), encryptedPassword);
    userRepo.save(user);

    // Add the token
    AuthenticationToken authenticationToken = new AuthenticationToken(user);
    tokenService.saveConfirmationToken(authenticationToken);

    ResponseDto responseDto = new ResponseDto("success", "User created successfully");
    return responseDto;
  }

  @Override
  public SignInResponseDto signin(SignInDto body) {
    // Find user by email
    User user = userRepo.findByEmail(body.getEmail());

    if (Objects.isNull(user))
      throw new AuthenticationFailureException("User does not exist");

    // Check password
    try {
      Boolean arePasswordsEqual = user.getPassword().equals(hashPassword(body.getPassword()));

      if (!arePasswordsEqual)
        throw new AuthenticationFailureException("Invalid credentials");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new AuthenticationFailureException(e.getMessage());
    }

    AuthenticationToken token = tokenService.getToken(user);

    if (Objects.isNull(token))
      throw new CustomException("Error occured while retrieving token");

    return new SignInResponseDto("success", token.getToken());
  }
}
