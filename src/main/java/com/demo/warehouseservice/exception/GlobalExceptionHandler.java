package com.demo.warehouseservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle exception
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(exception.getMessage());
    }

    /**
     * Handle not found exception
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {ArticleNotFoundException.class, ProductNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    /**
     * Handle format exception
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {FileFormatException.class})
    public ResponseEntity<String> handleFormatException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
