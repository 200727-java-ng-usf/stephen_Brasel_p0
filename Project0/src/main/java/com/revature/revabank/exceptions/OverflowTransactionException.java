package com.revature.revabank.exceptions;

/**
 * Thrown when a transaction would lead to overflow or infinite values,
 * i.e., wrapping from Integer.MAX_VALUE to Integer.MIN_VALUE because
 * of an attempt to add 1 to Integer.MAX_VALUE.
 */
public class OverflowTransactionException extends TransactionException {

	/**
	 * Creates this exception and prints
	 * "High-Value transactions more than Double.POSITIVE_INFINITY are not allowed."
	 * to the screen
	 */
	public OverflowTransactionException() {
		super("High-Value transactions more than Double.POSITIVE_INFINITY are not allowed.");
	}

	/**
	 * Creates this exception and prints message to the screen.
	 * @param message
	 */
	public OverflowTransactionException(String message) {
		super(message);
	}
}
