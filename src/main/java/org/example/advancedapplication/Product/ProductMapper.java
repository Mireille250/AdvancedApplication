package org.example.advancedapplication.Product;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDto productDto);
    Product updateProduct(ProductDto productDto , @MappingTarget Product product);
}
