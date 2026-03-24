package org.example.advancedapplication.customer;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false)
    private Long id;

    @Length(message = "the name length must be at least be 10 characters minimum", min = 10, max = 100)
    @NotBlank(message = "the customer's name must have  not be blank")
    @Column(name = "customer_name")
    private String name;

}