package com.revature.revabank.exceptions;

public class TransactionException extends RuntimeException {

	public TransactionException() {
		super("Transactions failed.");
	}

	public TransactionException(String message) {
		super(message);
	}
}
