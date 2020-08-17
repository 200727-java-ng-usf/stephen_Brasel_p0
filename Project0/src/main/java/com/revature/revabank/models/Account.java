package com.revature.revabank.models;

import java.util.Stack;

public abstract class Account {
	private String id;
	private String name;
	private double balance;
	private Stack<Transaction> history;

	public double deposit(double amount){
		//edge cases
		if(amount < 0){
			//TODO throw new custom exception
			throw new RuntimeException("Negative deposits are not allowed.");
		}
		if(amount + balance == Double.POSITIVE_INFINITY){ //
			//TODO throw new custom exception
			throw new RuntimeException("Infinite Money. You've crashed all economies.");
		}
		return balance += amount;
	}

	public double withdraw(double amount){
		//edge cases
		if(amount < 0){
			//TODO throw new custom exception
			throw new RuntimeException("Negative withdrawals are not allowed.");
		}
		if(Double.compare((balance - amount), 0) < 0){ //
			//TODO throw new custom exception
			throw new RuntimeException("Withdrawal failed: lack of funds.");
		}
		balance -= amount;
		return amount;
	}
}
