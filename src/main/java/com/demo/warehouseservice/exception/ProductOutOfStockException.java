package com.demo.warehouseservice.exception;

public class ProductOutOfStockException extends Exception {
    private static final long serialVersionUID = -6488989894093685649L;

    public ProductOutOfStockException(String message) {
        super(message);
    }
}