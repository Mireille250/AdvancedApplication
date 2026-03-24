package org.example.advancedapplication.Order;

import jakarta.transaction.Transactional;
import org.example.advancedapplication.Product.Product;
import org.example.advancedapplication.Product.ProductRepository;
import org.example.advancedapplication.customer.Customer;
import org.example.advancedapplication.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(CustomerRepository customerRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderMapper orderMapper) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;

    }

    @Transactional
    public Order createOrder(OrderRequest request) {


        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        Order order = orderMapper.toOrder(customer);

        List<OrderItem> items = new ArrayList<>();


        for (ItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));


            OrderItem item = orderMapper.toOrderItem(
                    order,
                    product,
                    itemRequest.getQuantity(),
                    product.getPrice_per_unit() * itemRequest.getQuantity()
            );


            OrderItemId id = new OrderItemId();
            id.setOrderId(order.getId());
            id.setProductId(product.getId());

            item.setId(id);

            items.add(item);
        }


        order.setItems(items);
        productRepository.findAll().stream().filter(product -> product.getName() == "zara").map(product -> product.getPrice_per_unit() - request.getItems().getLast().getQuantity());


        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
