package com.revature.revabank.screens;

import com.revature.revabank.services.AccountService;

import static com.revature.revabank.AppDriver.app;

public class NewAccountScreen extends Screen {
	private AccountService accountService;

	public NewAccountScreen(AccountService accountService) {
		super("NewAccountScreen", "/newAccount");
		this.accountService = accountService;
	}

	@Override
	public void render() {
		String balance, accountType, accountName;
		try{
			System.out.println("New User Account");
			System.out.println("+-----------------------------+");
			System.out.print("Account Type: \n"
					+ "1) Savings\n"
					+ "2) Checking\n"
			);
			accountType = app.getConsole().readLine().trim();
			System.out.println("Starting balance: ");
			balance = app.getConsole().readLine().trim();
			System.out.println("Account Name (Optional, leave blank for default): ");
			accountName = app.getConsole().readLine().trim();
			accountService.addAccount(balance, accountType, accountName);
		}catch(Exception e){
			System.out.println("Could not parse user input, please try again.");
//			e.printStackTrace();
		}
	}
}
