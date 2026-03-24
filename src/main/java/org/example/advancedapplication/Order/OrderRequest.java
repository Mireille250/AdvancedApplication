package org.example.advancedapplication.Order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private Long customerId;
    private List<ItemRequest> items;
}
