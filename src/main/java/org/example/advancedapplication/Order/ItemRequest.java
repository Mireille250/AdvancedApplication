package org.example.advancedapplication.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
    private Long productId;
    private Integer quantity;
}
