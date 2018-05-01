package com.example.exception;

/**
 * This exception which is a sub-class of {@link RuntimeException}
 * triggers in the cases that input arguments are not legal.
 */
public class IllegalArgumentException extends RuntimeException {

	public IllegalArgumentException(String msg) {
		super(msg);
	}
}
