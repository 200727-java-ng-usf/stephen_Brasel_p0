package com.revature.revabank.services;

import com.revature.revabank.models.*;
import com.revature.revabank.repos.AccountRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static com.revature.revabank.AppDriver.app;

public class AccountService {
	private AccountRepository accountRepo;
	private BigDecimal startingValueMinimum = BigDecimal.valueOf(100D);

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

	public Set<Account> getAccountsByUser(AppUser user) {
		return accountRepo.findAccountsByUserId(user.getId());
	}

	public Optional<Account> getAccountById(int id) {
		return accountRepo.findAccountsByUserId(id).stream().findFirst();
	}

	public Account getAccountByAccountName(String name) {
		return null;
	}

	public BigDecimal deposit(BigDecimal amount) {
		//edge cases
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			//TODO throw new custom exception
			throw new RuntimeException("Negative deposits are not allowed.");
		}
//		if (amount + app.getCurrentAccount().getBalance() == BigDecimal.) { //
//			//TODO throw new custom exception
//			throw new RuntimeException("Infinite Money. You've crashed all economies.");
//		}
		if(accountRepo.update(amount)) {
			app.getCurrentAccount().setBalance(app.getCurrentAccount().getBalance().add(amount));

			//TODO Get Transaction Description
			String description = "";
			int id = (LocalDate.now().toString() +
					description +
					amount +
					app.getCurrentAccount().toString() +
					app.getCurrentAccount().getBalance() +
					app.getCurrentAccount().getOwners().toString() +
					""
			).hashCode();
			Transaction t = new Transaction(id,
					description,
					amount,
					app.getCurrentAccount()
			);
			return app.getCurrentAccount().getBalance();
		} else {
			return BigDecimal.ZERO;
		}
	}

	/**
	 * Withdraws amount from account and returns the amount withdrawn.
	 *
	 * @param amount The amount to withdraw
	 * @return amount
	 */
	public BigDecimal withdraw(BigDecimal amount) {
		//edge cases
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			//TODO throw new custom exception
			throw new RuntimeException("Negative withdrawals are not allowed.");
		}
		if (app.getCurrentAccount().getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
			//TODO throw new custom exception
			throw new RuntimeException("Withdrawal failed: lack of funds.");
		}
		if(accountRepo.update(amount.negate())) {
			app.getCurrentAccount().setBalance(app.getCurrentAccount().getBalance().subtract(amount));
			return amount;
		} else{
			return BigDecimal.ZERO;
		}

	}

	public void addAccount(String balance, String accountType, String accountName){
		BigDecimal bal = BigDecimal.valueOf(Double.parseDouble(balance));
		if(bal.compareTo(startingValueMinimum) < 0){
			//TODO throw new custom exception
			throw new RuntimeException("Balance too small to start an account");
		}
		Account acc = new Account();
		acc.setBalance(bal);
		switch (accountType.toLowerCase()){
			case "1": case "saving": case "savings":
			default:
				acc.setType(AccountType.SAVING);
				break;
			case "2": case "checking": case "checkings":
				acc.setType(AccountType.CHECKING);
				break;
		}
		if(accountName == null || accountName.equals("")){
			acc.setName(acc.getType().toString());
		} else {
			acc.setName(accountName);
		}
		acc.addOwner(app.getCurrentUser());
		app.getCurrentUser().addAccount(acc);
		accountRepo.save(acc);
		app.setCurrentAccount(acc);
	}
	//endregion
}
