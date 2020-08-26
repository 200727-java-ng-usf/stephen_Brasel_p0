package com.revature.revabank.exceptions;

public class OverflowTransactionException extends TransactionException {

	public OverflowTransactionException() {
		super("High-Value transactions more than Double.POSITIVE_INFINITY are not allowed.");
	}

	public OverflowTransactionException(String message) {
		super(message);
	}
}
