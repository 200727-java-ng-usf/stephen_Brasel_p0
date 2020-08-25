package com.revature.revabank.models;

import java.util.Objects;
import java.util.Stack;
import java.math.BigDecimal;

public class Account {
	//region Fields
	private String id;
	private AppUser owner;
	private String name;
	private BigDecimal balance = BigDecimal.ZERO;
	private Stack<Transaction> history;
	private AccountType type;
	//endregion

	//region Constructors

	public Account() {
		super();
	}

	public Account(AccountType type) {
		this.type = type;
	}

	public Account(String id, AppUser owner, String name, BigDecimal balance, Stack<Transaction> history, AccountType type) {
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.balance = balance;
		this.history = history;
		this.type = type;
	}


	//endregion

	//region Getters and Setters
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Stack<Transaction> getHistory() {
		return history;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public void setHistory(Stack<Transaction> history) {
		this.history = history;
	}
	//endregion


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return Objects.equals(id, account.id) &&
				Objects.equals(owner, account.owner) &&
				Objects.equals(name, account.name) &&
				Objects.equals(balance, account.balance) &&
				Objects.equals(history, account.history) &&
				type == account.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, owner, name, balance, history, type);
	}

	@Override
	public String toString() {
		return "Account{" +
				"id='" + id + '\'' +
				", owner=" + owner +
				", name='" + name + '\'' +
				", balance=" + balance +
				", history=" + history +
				", type=" + type +
				'}';
	}
}
