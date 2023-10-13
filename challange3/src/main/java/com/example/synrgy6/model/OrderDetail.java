package com.example.synrgy6.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Integer quantity;

//    @ManyToOne
//    @JoinColumn(name = "order_id", referencedColumnName = "id")
//    private Orders orders;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    private Products products;


}
