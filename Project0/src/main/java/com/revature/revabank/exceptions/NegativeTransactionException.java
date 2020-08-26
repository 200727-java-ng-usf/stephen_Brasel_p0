package com.revature.revabank.exceptions;

/**
 * Thrown when a transaction was attempted with negative amounts,
 * i.e., trying to deposit negative money.
 */
public class NegativeTransactionException extends TransactionException {

	/**
	 * creates this exception and prints "Negative transactions are not allowed."
	 * to the screen.
	 */
	public NegativeTransactionException() {
		super("Negative transactions are not allowed.");
	}

	/**
	 * Creates this exception and prints message to the screen.
	 * @param message
	 */
	public NegativeTransactionException(String message) {
		super(message);
	}
}
