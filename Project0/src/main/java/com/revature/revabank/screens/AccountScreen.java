package com.revature.revabank.screens;

import com.revature.revabank.services.AccountService;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import static com.revature.revabank.AppDriver.app;

public class AccountScreen extends Screen{
	/**
	 "View Balance"<br>
	 ,"Withdraw"<br>
	 ,"Deposit"<br>
	 ,"Return to Account Manager"<br>
	 */
	private String[] options = {
			"View Balance"
			,"Withdraw"
			,"Deposit"
			,"Return to Dashboard"
	};
	private AccountService accountService;

	public AccountScreen(AccountService accountService){
		super("AccountScreen", "/account");
		this.accountService = accountService;
	}
	/**
	 * Renders the login screen menu to the console.
	 */
	@Override
	public void render(){
		NumberFormat us   = NumberFormat.getCurrencyInstance(Locale.US);
		String prompt;
		while(isScreenValid){
			System.out.println("Please choose an option:");
			for (int i = 1; i <= options.length; i++) {
				System.out.println(i + ") " + options[i-1]);
			}
			try{
				System.out.print("> ");
				prompt = app.getConsole().readLine().trim();

				switch (prompt){
					case "1":
						System.out.println(app.getCurrentAccount().getBalance());
						break;
					case "2":
						System.out.println("How much would you like to withdraw? ");
						System.out.print("> ");
						prompt = app.getConsole().readLine().trim();
						accountService.withdraw(BigDecimal.valueOf(Double.parseDouble(prompt)));
						break;
					case "3":
						System.out.println("How much would you like to deposit? ");
						System.out.print("> ");
						prompt = app.getConsole().readLine().trim();
						accountService.deposit(BigDecimal.valueOf(Double.parseDouble(prompt)));
						break;
					case "4":
						isScreenValid = false;
						break;
					default:
						System.out.println("[LOG] - Invalid Selection!");
						break;
				}
			} catch(Exception e) {
			    e.printStackTrace();
			}
		}

	}
}
