package com.example.synrgy6.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "completed")
    private Boolean completed;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private Users user;
//
//    @OneToMany(mappedBy = "order")
//    private List<OrderDetail> orderDetail;

}