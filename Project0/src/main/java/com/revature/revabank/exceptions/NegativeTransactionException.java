package com.revature.revabank.exceptions;

public class NegativeTransactionException extends TransactionException {

	public NegativeTransactionException() {
		super("Negative transactions are not allowed.");
	}

	public NegativeTransactionException(String message) {
		super(message);
	}
}
