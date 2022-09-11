package com.ibm.productservice.exception;

public class ProductNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Integer id) {

        super(String.format("Product with Id %d not found", id));
    }


}
