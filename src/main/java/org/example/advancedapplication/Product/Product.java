package org.example.advancedapplication.Product;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.advancedapplication.Enum.Category;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Double price_per_unit;

    private static int quantity = 20;


}