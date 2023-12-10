package com.binarfud.proplayer.challange5.controllers;

import com.binarfud.proplayer.challange5.dto.user.request.UserRequestChangePWDTO;
import com.binarfud.proplayer.challange5.dto.user.request.UserRequestDTO;
import com.binarfud.proplayer.challange5.dto.user.response.UserResponseByIdDTO;
import com.binarfud.proplayer.challange5.dto.user.response.UserResponseChangePW;
import com.binarfud.proplayer.challange5.dto.user.response.UserResponseDTO;
import com.binarfud.proplayer.challange5.dto.user.response.UserResponseListDTO;
import com.binarfud.proplayer.challange5.exception.UserRegistrationException;
import com.binarfud.proplayer.challange5.services.InvoiceService;
import com.binarfud.proplayer.challange5.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private final UsersService userService;
    private final InvoiceService invoiceService;

    @Autowired
    public UserController(UsersService userService, InvoiceService invoiceService) {
        this.userService = userService;
        this.invoiceService = invoiceService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userDTO) {
        try {
            UserRequestDTO registeredUser = userService.registerUser(userDTO);
            if (registeredUser != null) {
                log.info("Registration successful for user: {}", registeredUser.getUsername());
                return ResponseEntity.ok(new UserResponseDTO(registeredUser, "Registration successful"));
            } else {
                log.error("Registration failed for user: {}", userDTO.getUsername());
                return ResponseEntity.ok(new UserResponseDTO((UUID) null, "Registration Failed"));
            }
        } catch (UserRegistrationException e) {
            log.error("Registration failed for user: {}", userDTO.getUsername(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseDTO((UUID) null, e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody UserRequestDTO userDTO) {
        try {
            UserRequestDTO loggedInUser = userService.loginUser(userDTO);
            if (loggedInUser != null) {
                log.info("Login successful for user: {}", loggedInUser.getUsername());
                return ResponseEntity.ok(new UserResponseDTO(loggedInUser, "Login successful"));
            } else {
                log.warn("Login failed for user: {}", userDTO.getUsername());
                return ResponseEntity.ok(new UserResponseDTO(loggedInUser, "Login failed. Invalid credentials."));
            }
        } catch (Exception e) {
            log.error("Internal server error while processing login for user: {}", userDTO.getUsername(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserResponseDTO((UUID) null, "Internal server error"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseByIdDTO> getUserByID(@PathVariable UUID id) {
        try {
            UserResponseByIdDTO userResponseByIdDTO = userService.getUserByUUID(id);
            return ResponseEntity.ok(userResponseByIdDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("")
    public ResponseEntity<UserResponseChangePW> updateUserPasswordByUsername(@RequestBody UserRequestChangePWDTO userDTO) {
        try {
            UserResponseChangePW userChangePW = userService.changePwByUsername(userDTO);
            return ResponseEntity.ok(userChangePW);
        } catch (Exception e) {
            log.error("Internal server error while processing login for user: {}", userDTO.getUsername(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserResponseChangePW("Internal server error " + e.getMessage()));
        }
    }

    @GetMapping("/ordered-food")
    public ResponseEntity<?> getListUserOrderedFood() {
        List<UserResponseListDTO> orderedFoodList = userService.getListUserOrderedFood();
        if (orderedFoodList.isEmpty()) {
            log.error("Products not found.");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } else {
            return ResponseEntity.ok(orderedFoodList);
        }
    }

    @PostMapping("/generateInvoice/{userId}")
    public ResponseEntity<?> getInvoiceById(@PathVariable UUID userId) {
        byte[] pdfBytes = invoiceService.generateInvoice(userId);

        if (pdfBytes != null) {
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
