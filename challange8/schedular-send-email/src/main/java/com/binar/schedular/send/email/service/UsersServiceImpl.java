package com.binar.schedular.send.email.service;

import com.binar.schedular.send.email.model.Users;
import com.binar.schedular.send.email.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UserService{

    @Autowired
    private UsersRepository usersRepository;
    @Override
    public List<Users> getListUser() {
        List<Users> userList = usersRepository.findAll();
        if(userList.isEmpty()){
            return null;
        }
        return userList;
    }
}
