package com.demo.warehouseservice.exception;

public class ProductValidationException extends Exception {
    private static final long serialVersionUID = 126636786420667279L;

    public ProductValidationException(String message) {
        super(message);
    }
}
