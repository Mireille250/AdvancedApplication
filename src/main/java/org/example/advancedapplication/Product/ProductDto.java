package org.example.advancedapplication.Product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.example.advancedapplication.Enum.Category;

/**
 * DTO for {@link Product}
 */
@Data
public class ProductDto  {
    @NotBlank(message = "provide the product's name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;
    @PositiveOrZero(message = "the price of product must not be negative")
    private Double price_per_unit;
}
