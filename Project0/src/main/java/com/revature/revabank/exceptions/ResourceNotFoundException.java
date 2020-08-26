package com.revature.revabank.exceptions;

/**
 * Thrown when an item from a repository cannot be located,
 * i.e., an account was not located in a search.
 */
public class ResourceNotFoundException extends RuntimeException{
	/**
	 * Creates this exception and prints message to the screen.
	 * @param message
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
