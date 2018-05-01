package com.example.exception;

/**
 * This exception which is a sub-class of {@link RuntimeException}
 * triggers in the cases that input arguments are not sufficient
 * and at least one critical input is missing.
 */

public class InsufficientArgumentException extends RuntimeException {

	public InsufficientArgumentException(String msg) {
		super(msg);
	}
}
