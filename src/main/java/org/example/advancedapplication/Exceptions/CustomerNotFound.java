package org.example.advancedapplication.Exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerNotFound extends RuntimeException {
    private String name;
    private Long id;


    public CustomerNotFound(String message , Long id) {
        super(message);
        this.id = id;

    }
    public CustomerNotFound(String message , String name) {
        super(message);
        this.name = name;

    }
}