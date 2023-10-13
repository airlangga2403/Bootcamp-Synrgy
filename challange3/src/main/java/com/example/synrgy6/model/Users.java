package com.example.synrgy6.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Users(String username, String emailAddress, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    @Column(name = "username")
    private String username;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "password")
    private String password;

}