package org.example.advancedapplication.customer;

import org.example.advancedapplication.Exceptions.CustomerNotFound;
import org.example.advancedapplication.response.AddResponse;
import org.example.advancedapplication.response.UpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public CustomerService(CustomerRepository customerRepository , CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public AddResponse addCustomer(CustomerDto customer) {
        customerRepository.save(customerMapper.mapToEntity(customer));
        return new AddResponse(customer.getName() + " successfully added" , HttpStatus.ACCEPTED);
    }
    public Customer getCustomer(Long id) {

        return  customerRepository.findById(id).orElseThrow(() -> new CustomerNotFound("no customer found with such id" , id));
    }
    public Customer getCustomer(String name) {

        return  customerRepository.findCustomerByNameContainingIgnoreCase(name).orElseThrow(() -> new CustomerNotFound("no customer found with the providec name" , name));
    }
    public List<Customer> getAllCustomers() {
        return  customerRepository.findAll();
    }
    public UpdateResponse updateCustomer(String name , CustomerDto customer) {
        var customerToUpdate = customerRepository.findCustomerByNameContainingIgnoreCase(name).orElseThrow(() -> new CustomerNotFound("no customer found" , name));

        customerRepository.save(customerMapper.updateEntity(customer, customerToUpdate));

        return new UpdateResponse(customerToUpdate.getName() , customer.getName() , "successfully updated");

    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

