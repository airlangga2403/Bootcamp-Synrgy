package com.org.challange4.controllers;

import com.org.challange4.dto.user.request.UserRequestDTO;
import com.org.challange4.dto.user.response.UserResponseDTO;
import com.org.challange4.exception.UserRegistrationException;
import com.org.challange4.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsersService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDTO userDTO) {
        try {
            UserRequestDTO registeredUser = userService.registerUser(userDTO);
            if (registeredUser != null) {
                log.info("Registration successful for user: {}", registeredUser.getUsername());
                return new ResponseEntity<>(new UserResponseDTO(registeredUser, "Registration successful"), HttpStatus.OK);
            }
            log.error("Registration failed for user: {}", userDTO.getUsername());
            return new ResponseEntity<>(new UserResponseDTO(null, "Registration Failed"), HttpStatus.OK);
        } catch (UserRegistrationException e) {
            log.error("Registration failed for user: {}", userDTO.getUsername(), e);
            return new ResponseEntity<>(new UserResponseDTO(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequestDTO userDTO) {
        try {
            UserRequestDTO loggedInUser = userService.loginUser(userDTO);
            if (loggedInUser != null) {
                log.info("Login successful for user: {}", loggedInUser.getUsername());
                return new ResponseEntity<>(new UserResponseDTO(loggedInUser, "Login successful"), HttpStatus.OK);
            } else {
                log.warn("Login failed for user: {}", userDTO.getUsername());
                return new ResponseEntity<>(new UserResponseDTO(loggedInUser, "Login failed. Invalid credentials."), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error("Internal server error while processing login for user: {}", userDTO.getUsername(), e);
            return new ResponseEntity<>(new UserResponseDTO(null, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}