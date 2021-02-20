package com.demo.warehouseservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InventoryException extends Exception{

	
	private static final long serialVersionUID = 17775809809L;

	private String errorCode;
	
	private String errorMessage;
}
