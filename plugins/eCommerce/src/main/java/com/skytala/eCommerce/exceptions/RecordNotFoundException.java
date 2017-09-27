package com.skytala.eCommerce.exceptions;

import java.util.NoSuchElementException;

/**
 * Thrown to indicate that requested record was not found
 * 
 * @author work
 *
 */
public class RecordNotFoundException extends NoSuchElementException {
	
	
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public RecordNotFoundException(Class<?> entity) {
		super(entity.getName());

	}
	
	public RecordNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
