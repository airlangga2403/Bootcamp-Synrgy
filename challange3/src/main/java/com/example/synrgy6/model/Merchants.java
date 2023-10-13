package com.example.synrgy6.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "merchants")
public class Merchants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_location")
    private String merchantLocation;

    @Column(name = "open")
    private Boolean open;


}
