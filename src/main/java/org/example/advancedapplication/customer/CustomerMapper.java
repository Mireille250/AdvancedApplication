package org.example.advancedapplication.customer;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel= "spring")
public interface CustomerMapper {

    Customer mapToEntity(CustomerDto customerDto);
    Customer updateEntity(CustomerDto customerDto , @MappingTarget Customer customer);
}
