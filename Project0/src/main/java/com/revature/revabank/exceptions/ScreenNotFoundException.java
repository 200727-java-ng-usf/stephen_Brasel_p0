package com.revature.revabank.exceptions;

/**
 * Thrown when a route to a screen is no longer servicable,
 * i.e., /account/Henry no longer exists due to Henry being deleted.
 */
public class ScreenNotFoundException extends RuntimeException {

	/**
	 * Creates this exception and prints message to the screen.
	 * @param message
	 */
	public ScreenNotFoundException(String message) {
		super(message);
	}
}
