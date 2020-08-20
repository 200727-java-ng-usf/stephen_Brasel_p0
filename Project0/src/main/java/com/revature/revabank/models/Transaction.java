package com.revature.revabank.models;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
	private String id;
	private LocalDate date;
	private String description;
	private double amount;
	private Account account;
	private double balance;
	private AppUser owner;
	private AppUser transferee;

	public Transaction(String id, String description, double amount, Account account, AppUser owner, AppUser transferee) {
		this.id = id;
		this.date = LocalDate.now();
		this.description = description;
		this.amount = amount;
		this.account = account;
		this.balance = account.getBalance();
		this.owner = owner;
		this.transferee = transferee;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Transaction that = (Transaction) o;
		return Double.compare(that.amount, amount) == 0 &&
				Double.compare(that.balance, balance) == 0 &&
				Objects.equals(id, that.id) &&
				Objects.equals(date, that.date) &&
				Objects.equals(description, that.description) &&
				Objects.equals(account, that.account) &&
				Objects.equals(owner, that.owner) &&
				Objects.equals(transferee, that.transferee);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, date, description, amount, account, balance, owner, transferee);
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"id='" + id + '\'' +
				", date=" + date +
				", account=" + account +
				", amount=" + amount +
				", balance=" + balance +
				", owner=" + owner +
				", transferee=" + transferee +
				", description='" + description + '\'' +
				'}';
	}
}
