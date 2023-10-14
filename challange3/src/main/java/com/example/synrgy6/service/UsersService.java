package com.example.synrgy6.service;

import com.example.synrgy6.model.Users;
import com.example.synrgy6.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users saveUsers(Users users) {
        return usersRepository.save(users);
    }

    public Users findbyUsername(String users) {
        return usersRepository.findByUsername(users);
    }


}
