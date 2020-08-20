package com.revature.revabank.models;

import java.util.Stack;

public abstract class Account {
	private String id;
	private AppUser owner;
	private String name;
	private double balance;
	private Stack<Transaction> history;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AppUser getOwner() {
		return owner;
	}

	public void setOwner(AppUser owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Stack<Transaction> getHistory() {
		return history;
	}

	public void setHistory(Stack<Transaction> history) {
		this.history = history;
	}
}
