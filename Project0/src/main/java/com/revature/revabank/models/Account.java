package com.revature.revabank.models;

import java.util.*;

public class Account {
	//region Fields
	private int id;
	private Double balance = 0D;
	private AccountType type = AccountType.CHECKING;
	private String name = type.toString();
	private Set<AppUser> owners;
	private ArrayList<Transaction> history;
	//endregion

	//region Constructors

	public Account() {
		super();
		owners = new HashSet<>();
		history = new ArrayList<>();
	}

	public Account(int id, Set<AppUser> owners, String name, Double balance, ArrayList<Transaction> history, AccountType type) {
		this.id = id;
		this.owners = owners;
		this.name = name;
		this.balance = balance;
		this.history = history;
		this.type = type;
	}


	//endregion

	//region Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<AppUser> getOwners() {
		return owners;
	}

	public void setOwners(Set<AppUser> owners) {
		this.owners = owners;
	}

	public void addOwner(AppUser user){
		owners.add(user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public ArrayList<Transaction> getHistory() {
		return history;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public void setHistory(ArrayList<Transaction> history) {
		this.history = history;
	}
	//endregion

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return Objects.equals(id, account.id) &&
				Objects.equals(owners, account.owners) &&
				Objects.equals(name, account.name) &&
				Objects.equals(balance, account.balance) &&
				Objects.equals(history, account.history) &&
				type == account.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, owners, name, balance, history, type);
	}

	@Override
	public String toString() {
		return "Account{" +
				"id='" + id + '\'' +
//				", owners=" + Arrays.toString(owners.stream().map(elem -> elem.toString(false)).toArray()) +
				", name='" + name + '\'' +
				", balance=$" + balance +
				", history=" + history +
				", type=" + type +
				'}';
	}
}
