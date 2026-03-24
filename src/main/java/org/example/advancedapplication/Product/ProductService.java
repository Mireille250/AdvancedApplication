package org.example.advancedapplication.Product;


import org.example.advancedapplication.Exceptions.ProductNotFound;
import org.example.advancedapplication.response.AddResponse;
import org.example.advancedapplication.response.UpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper productMapper;
    public ProductService(ProductRepository repository , ProductMapper productMapper) {

        this.repository = repository;
        this.productMapper = productMapper;
    }


    public AddResponse addProduct(ProductDto product) {
        repository.save(productMapper.toProduct(product));
        return new AddResponse(product.getName() + " added" , HttpStatus.CREATED);
    }
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    public Product getProduct(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFound("the product with such an ID not found "));
    }
    public Product getProduct(String name) {
        return repository.findProductByNameContainingIgnoreCase(name).orElseThrow(() -> new ProductNotFound("there is no product found"));
    }

    public UpdateResponse updateProduct(Long id, ProductDto productDto) {
        Product product = getProduct(id);
        repository.save(productMapper.updateProduct(productDto , product));
        return  new UpdateResponse(product.getName(), productDto.getName(), "product updated");

    }
    public void deleteProduct(String name) {
        Product product = repository.findProductByNameContainingIgnoreCase(name).orElseThrow(() -> new ProductNotFound("the product with such an name not found"));
        repository.delete(product);
    }
}
