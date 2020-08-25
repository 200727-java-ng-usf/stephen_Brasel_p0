package com.revature.revabank.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
	private int id;
	private LocalDate date;
	private String description;
	private BigDecimal amount;
	private Account account;
	private BigDecimal balance;

	public Transaction(int id, String description, BigDecimal amount, Account account) {
		this.id = id;
		this.date = LocalDate.now();
		this.description = description;
		this.amount = amount;
		this.account = account;
		this.balance = account.getBalance();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Transaction that = (Transaction) o;
		return id == that.id &&
				Objects.equals(date, that.date) &&
				Objects.equals(description, that.description) &&
				Objects.equals(amount, that.amount) &&
				Objects.equals(account, that.account) &&
				Objects.equals(balance, that.balance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, date, description, amount, account, balance);
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"id='" + id + '\'' +
				", date=" + date +
				", account=" + account +
				", amount=" + amount +
				", balance=" + balance +
				", description='" + description + '\'' +
				'}';
	}
}
