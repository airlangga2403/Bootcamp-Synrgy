package com.org.challange4.services;

import com.org.challange4.dto.user.request.UserRequestDTO;
import com.org.challange4.models.Users;
import com.org.challange4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public UserRequestDTO registerUser(UserRequestDTO userDTO) {
        Optional<Users> user = userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        if (!user.isPresent()) {
            Users registeredUser = userRepository.save(new Users(userDTO.getUsername(), userDTO.getEmailAddress(), userDTO.getPassword()));
            return convertToUserDTO(registeredUser);
        } else {
            return null;
        }

    }

    public UserRequestDTO loginUser(UserRequestDTO userDTO) {
        Optional<Users> user = userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        if (user.isPresent()) {
            return convertToUserDTO(user.get());
        } else {
            return null;
        }
    }


    private UserRequestDTO convertToUserDTO(Users user) {
        UserRequestDTO userDTO = new UserRequestDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmailAddress(user.getEmailAddress());
        return userDTO;
    }
}
