package com.demo.warehouseservice.exception;

public class ArticleNotFoundException extends Exception {
    private static final long serialVersionUID = 126636786420667279L;

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
