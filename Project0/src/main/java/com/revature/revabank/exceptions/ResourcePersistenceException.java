package com.revature.revabank.exceptions;

/**
 * Thrown when a resource that has attempted to be saved cannot be.
 */
public class ResourcePersistenceException extends RuntimeException{

	/**
	 * Creates this exception and prints message to the screen.
	 * @param message
	 */
	public ResourcePersistenceException(String message) {
		super(message);
	}
}
