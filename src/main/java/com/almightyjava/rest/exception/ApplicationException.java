package com.almightyjava.rest.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -4140957465302218344L;

	public ApplicationException(String message) {
		super(message);
	}

}
