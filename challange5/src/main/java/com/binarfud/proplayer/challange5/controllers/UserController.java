package com.binarfud.proplayer.challange5.controllers;

import com.binarfud.proplayer.challange5.dto.user.request.UserRequestChangePWDTO;
import com.binarfud.proplayer.challange5.dto.user.request.UserRequestDTO;
import com.binarfud.proplayer.challange5.dto.user.response.UserResponseByIdDTO;
import com.binarfud.proplayer.challange5.dto.user.response.UserResponseChangePW;
import com.binarfud.proplayer.challange5.dto.user.response.UserResponseDTO;
import com.binarfud.proplayer.challange5.dto.user.response.UserResponseListDTO;
import com.binarfud.proplayer.challange5.exception.UserRegistrationException;
import com.binarfud.proplayer.challange5.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UsersService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userDTO) {
        try {
            UserRequestDTO registeredUser = userService.registerUser(userDTO);
            if (registeredUser != null) {
                log.info("Registration successful for user: {}", registeredUser.getUsername());
                return new ResponseEntity<>(new UserResponseDTO(registeredUser, "Registration successful"), HttpStatus.OK);
            }
            log.error("Registration failed for user: {}", userDTO.getUsername());
            return new ResponseEntity<>(new UserResponseDTO((UUID) null, "Registration Failed"), HttpStatus.OK);
        } catch (UserRegistrationException e) {
            log.error("Registration failed for user: {}", userDTO.getUsername(), e);
            return new ResponseEntity<>(new UserResponseDTO((UUID) null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody UserRequestDTO userDTO) {
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
            return new ResponseEntity<>(new UserResponseDTO((UUID) null, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get User by UUID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseByIdDTO> getUserByID(@PathVariable UUID id) {
        try {
            UserResponseByIdDTO userResponseByIdDTO = userService.getUserByUUID(id);
            return new ResponseEntity<>(userResponseByIdDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update User Password by Username
    @PutMapping("")
    public ResponseEntity<UserResponseChangePW> updateUserPasswordByUsername(@RequestBody UserRequestChangePWDTO userDTO) {
        try {
            UserResponseChangePW userChangePW = userService.changePwByUsername(userDTO);
            return new ResponseEntity<>(userChangePW, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Internal server error while processing login for user: {}", userDTO.getUsername(), e);
            return new ResponseEntity<>(new UserResponseChangePW("Internal server error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ordered-food")
    public ResponseEntity<?> getListUserOrderedFood() {

        List<UserResponseListDTO> orderedFoodList = userService.getListUserOrderedFood();
        if (orderedFoodList.isEmpty()) {
            log.error("Products not found.");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Product not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(orderedFoodList);
        }



    }


}