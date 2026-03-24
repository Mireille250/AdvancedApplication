package org.example.advancedapplication.Order;


import org.example.advancedapplication.Product.Product;
import org.example.advancedapplication.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    // Create Order from Customer
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toOrder(Customer customer);

    // Create OrderItem
    @Mapping(target = "id", ignore = true)
    OrderItem toOrderItem(Order order, Product product, Integer quantity , Double price);

}

