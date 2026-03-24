package org.example.advancedapplication.customer;

import org.example.advancedapplication.response.AddResponse;
import org.example.advancedapplication.response.UpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name= "Customer API" , description = "this is the api regarding the customers, every activities that can be  performed by the user himself/herself" )
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service) {
        this.service = service;
    }


    @PostMapping
    @Operation(summary = "Adding the new customer")
    public ResponseEntity<AddResponse> createCustomer(@Valid @RequestBody CustomerDto customer) {
        return new ResponseEntity<>(service.addCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "getting all customers that are saved")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return  new ResponseEntity<>(service.getAllCustomers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "getting user by their id")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return  new ResponseEntity<>(service.getCustomer(id), HttpStatus.OK);
    }
    @GetMapping("/name")
    @Operation(summary = "getting the customer  using their name")
    public ResponseEntity<Customer> getCustomerByName(@RequestParam String name) {
        return new ResponseEntity<>(service.getCustomer(name), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "updating the customer with the help of their name")
    public ResponseEntity<UpdateResponse> updateCustomer(@Valid @RequestBody CustomerDto customer , @RequestParam String name) {
        return  new ResponseEntity<>(service.updateCustomer(name, customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deleting the customer by their Id")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
