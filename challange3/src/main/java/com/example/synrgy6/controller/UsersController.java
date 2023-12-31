package com.example.synrgy6.controller;

import com.example.synrgy6.model.Users;
import com.example.synrgy6.service.UsersService;
import com.example.synrgy6.utils.Utils;
import com.example.synrgy6.view.BinarFudView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class UsersController {

    private final UsersService usersService;

    private final BinarFudView view = new BinarFudView();
    private static final Scanner inpScanner = new Scanner(System.in);

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    public void add(Users users) {
        usersService.saveUsers(users);
    }

    public void welcomeUser() {
        view.welcomeMessage();
        int inpUser = inpScanner.nextInt();
        if (inpUser == 1) {
            loginUser();
        } else if (inpUser == 2) {
            registUser();
        } else {
            view.errorInputMessage();
        }

    }

    private void registUser() {
        view.inputUsername();
        String inpUsername = inpScanner.next();
        view.inputEmail();
        String inputEmail = inpScanner.next();
        view.inputPassword();
        String inpPass = inpScanner.next();

        Users users = new Users(inpUsername, inputEmail, inpPass);
        usersService.saveUsers(users);
        while (true) {
            loginUser();
        }

    }

    private void loginUser() {
        view.loginUser();
        view.inputUsername();
        String inpUsername = inpScanner.next();
        view.inputPassword();
        String inpPass = inpScanner.next();
        Users getUsers = usersService.findbyUsername(inpUsername);

        if (getUsers == null) {
            view.userNotFound();
            loginUser();
            return;
        }

        if (inpPass.equals(getUsers.getPassword()) && getUsers != null) {
            System.out.println(Utils.SEPARATOR);
            view.passwordBenar();
            System.out.println(Utils.SEPARATOR);
        } else {
            view.passwordSalah();
            int intChoice = inpScanner.nextInt();
            if (intChoice == 99) {
                loginUser();
            } else {
                System.exit(0); // Exit the application
            }
        }
    }

    public void processOrder() {

    }


}
