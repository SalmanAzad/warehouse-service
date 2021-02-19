package com.demo.warehouseservice.exception;

public class ProductNotFoundException extends Exception {
    private static final long serialVersionUID = 126636786420667279L;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
