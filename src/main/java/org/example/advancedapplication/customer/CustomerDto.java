package org.example.advancedapplication.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * DTO for {@link com.springboot.example.springbootappllication.Customer.Customer}
 */
@Data
public class CustomerDto{
    @NotBlank(message = "the customer's name must have  not be blank")
    @Length(message = "the name length must be at least be 10 characters minimum", min = 10, max = 100)
    private String name;
}