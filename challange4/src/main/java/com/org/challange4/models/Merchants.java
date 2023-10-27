package com.org.challange4.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "merchants")
public class Merchants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_location")
    private String merchantLocation;

    @Column(name = "open")
    private Boolean open;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.EAGER)
    private List<Products> products;

    public Merchants(String merchantName, String merchantLocation) {
        this(merchantName, merchantLocation, false);
    }

    public Merchants(String merchantName, String merchantLocation, boolean open) {
        this.merchantName = merchantName;
        this.merchantLocation = merchantLocation;
        this.open = open;
    }


}
