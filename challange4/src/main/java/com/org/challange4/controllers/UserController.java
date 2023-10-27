package com.org.challange4.controllers;

import com.org.challange4.dto.user.request.UserRequestDTO;
import com.org.challange4.dto.user.response.UserResponseDTO;
import com.org.challange4.exception.UserRegistrationException;
import com.org.challange4.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                return new ResponseEntity<>(new UserResponseDTO(registeredUser, "Registration successful"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new UserResponseDTO(registeredUser, "Registration Failed"), HttpStatus.OK);
        } catch (UserRegistrationException e) {
            return new ResponseEntity<>(new UserResponseDTO(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequestDTO userDTO) {
        try {
            UserRequestDTO loggedInUser = userService.loginUser(userDTO);
            if (loggedInUser != null) {
                return new ResponseEntity<>(new UserResponseDTO(loggedInUser, "Login successful"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new UserResponseDTO(loggedInUser, "Login failed. Invalid credentials."), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponseDTO(null, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
