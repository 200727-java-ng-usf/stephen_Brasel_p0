package com.revature.revabank.exceptions;

/**
 * Thrown when a transaction or multiple transactions have failed,
 * i.e., due to lack of funds.
 */
public class TransactionException extends RuntimeException {

	/**
	 * Creates this exception and prints Transactions failed.
	 * to the screen.
	 */
	public TransactionException() {
		super("Transactions failed.");
	}

	/**
	 * Creates this exception and prints message to the screen.
	 * @param message
	 */
	public TransactionException(String message) {
		super(message);
	}
}
