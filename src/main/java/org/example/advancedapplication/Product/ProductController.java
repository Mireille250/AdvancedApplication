package org.example.advancedapplication.Product;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.advancedapplication.response.AddResponse;
import org.example.advancedapplication.response.UpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name="Product API" , description = "Product api that contains all the http request regarding the products ")
public class ProductController {

    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }


    @PostMapping
    @Operation(summary = "adding the new product")
    public ResponseEntity<AddResponse> AddProduct(@Valid @RequestBody ProductDto product) {
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "getting the list of all product")
    public ResponseEntity<List<Product>> getAllProducts() {
        return  new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "getting the product using its id")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return  new ResponseEntity<>(service.getProduct(id), HttpStatus.OK);
    }
    @GetMapping("/product")
    @Operation(summary = "getting the product using their name")
    public ResponseEntity<Product> getProduct(@RequestParam String name) {
        return new ResponseEntity<>(service.getProduct(name), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "updating the product with a specific name")
    public ResponseEntity<UpdateResponse> updateProduct(@Valid @RequestBody ProductDto product , @RequestParam Long id) {
        return  new ResponseEntity<>(service.updateProduct(id ,product ), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    @Operation(summary = "deleting the product with a specific name")
    public ResponseEntity<?> deleteProduct(@PathVariable String name) {
        service.deleteProduct(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
