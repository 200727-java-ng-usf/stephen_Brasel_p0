package com.revature.revabank.models;

import java.util.*;

/**
 * Account holds information for a Bank Account, including the balance, the owners of the
 * account, and the transaction history.
 */
public class Account {
	//region Fields
	/**
	 * Unique identifier for this instance of the account.
	 */
	private int id;
	/**
	 * The total balance for this account.
	 */
	private Double balance = 0D;
	/**
	 * The type of account that this instance is,
	 * i.e., Checking or Saving
	 */
	private AccountType type = AccountType.CHECKING;
	/**
	 * The name of the account. This is user-specific.
	 */
	private String name = type.toString();
	/**
	 * The joint account holders.
	 */
	private Set<AppUser> owners;
	/**
	 * The transaction history of the account.
	 */
	private ArrayList<Transaction> history;
	//endregion

	//region Constructors

	/**
	 * Creates a new account instance.
	 */
	public Account() {
		super();
		owners = new HashSet<>();
		history = new ArrayList<>();
	}

	/**
	 * Creates a populated instance of an account by transferring the values given to the internal fields.
	 * @param id an int id. This is unnecessary to populate since the database will serialize this field.
	 * @param owners the joint owners of this account.
	 * @param name the name the user wants to refer to this account by.
	 * @param balance the starting balance for this account.
	 * @param history the transaction history, if any, for this account.
	 * @param type the account type, e.g., Checking or Saving
	 */
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

	/**
	 * Returns the ID
	 * @return the database-serialized id for this account.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets this account instance's id.
	 * @param id an int value. This method should only be used by referring to the id of the database instance.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the joint owners of this account.
	 * @return a Set of <code>{@link AppUser}</code>s that share ownership of this account.
	 */
	public Set<AppUser> getOwners() {
		return owners;
	}

	/**
	 * Sets the owners for this account to the passed parameters.
	 * @param owners the new <code>{@link AppUser}</code> owners of this account.
	 */
	public void setOwners(Set<AppUser> owners) {
		this.owners = owners;
	}

	/**
	 * Adds a new joint owner to the current Set.
	 * @param user the new joint owner <code>{@link AppUser}</code>.
	 */
	public void addOwner(AppUser user){
		owners.add(user);
	}

	/**
	 * Returns the name of the account. User Specified.
	 * @return a String holding the name of the account.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the account.
	 * @param name the new name for the account.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the total balance for this account.
	 * @return a Double holding the total balance for this account.
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * Returns a formatted String holding the balance,
	 * i.e., for a given balance $250, returns $250.00
	 * @return the balance in "$[amount].##" form
	 */
	public String getFormattedBalance() {
		return String.format("$%.2f", balance);
	}

	/**
	 * Sets the balance for this account.
	 * @param balance the new balance.
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * Returns the ArrayList of <code>{@link Transaction}</code>s for this account.
	 * @return the ArrayList of <code>{@link Transaction}</code>s for this account.
	 */
	public ArrayList<Transaction> getHistory() {
		return history;
	}

	/**
	 * Returns the <code>{@link AccountType}</code> for this account,
	 * e.g., <code>{@link AccountType}</code>.CHECKING
	 * @return the <code>{@link AccountType}</code> for this account
	 */
	public AccountType getType() {
		return type;
	}

	/**
	 * Sets the <code>{@link AccountType}</code> for this account.
	 * @param type the new <code>{@link AccountType}</code>.
	 */
	public void setType(AccountType type) {
		this.type = type;
	}

	/**
	 * Sets the transactional history of this account.
	 * @param history the new history of transactions.
	 */
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
				", balance=" + getFormattedBalance() +
				", history=" + history +
				", type=" + type +
				'}';
	}
}
