package org.example.advancedapplication.Order;

import org.example.advancedapplication.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;



    @CreationTimestamp
    private LocalDateTime orderDate;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();
}
