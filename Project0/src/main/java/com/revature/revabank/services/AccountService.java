package com.revature.revabank.services;

import com.revature.revabank.models.Account;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Transaction;
import com.revature.revabank.repos.AccountRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class AccountService {
	private AccountRepository accountRepo;

	//region Constructors

	public AccountService(AccountRepository accountRepo) {
		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
		this.accountRepo = accountRepo;
	}

	//endregion

	//region Methods
	public Set<Account> getAllAccounts() {
		return null;
	}

	public Set<Account> getAccountsByUser() {
		return null;
	}

	public Set<Account> getAccountsById() {
		return null;
	}

	public Account getAccountById(int id) {
		return null;
	}

	public Account getAccountByAccountName(String name) {
		return null;
	}

	public double deposit(Account account, double amount) {
		//edge cases
		if (amount < 0) {
			//TODO throw new custom exception
			throw new RuntimeException("Negative deposits are not allowed.");
		}
		if (amount + account.getBalance() == Double.POSITIVE_INFINITY) { //
			//TODO throw new custom exception
			throw new RuntimeException("Infinite Money. You've crashed all economies.");
		}
		account.setBalance(account.getBalance() + amount);

		//TODO Get Transaction Description
		String description = "";
		String id = Integer.toString(
				(LocalDate.now().toString() +
						description +
						amount +
						account.toString() +
						account.getBalance() +
						account.getOwner().toString() +
						""
				)
						.hashCode());
		Transaction t = new Transaction(id,
				description,
				amount,
				account,
				account.getOwner(),
				null
		);
		return account.getBalance();
	}

	/**
	 * Withdraws amount from account and returns the amount withdrew.
	 *
	 * @param amount The amount to withdraw
	 * @return amount
	 */
	public double withdraw(double amount) {
		//edge cases
		if (amount < 0) {
			//TODO throw new custom exception
			throw new RuntimeException("Negative withdrawals are not allowed.");
		}
		if (Double.compare((balance - amount), 0) < 0) { //
			//TODO throw new custom exception
			throw new RuntimeException("Withdrawal failed: lack of funds.");
		}
		balance -= amount;
		return amount;
	}
	//endregion
}
