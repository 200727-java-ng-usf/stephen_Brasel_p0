package com.revature.revabank.screens;

import com.revature.revabank.models.Account;
import com.revature.revabank.repos.AccountRepository;
import com.revature.revabank.services.AccountService;

import java.io.IOException;

import static com.revature.revabank.AppDriver.app;

public class AccountManagerScreen extends Screen{
	private AccountService accountService;
	public AccountManagerScreen(AccountService accountService){
		super("AccountManagerScreen", "/accounts");
		this.accountService = accountService;
	}

	/**
	 * Renders the login screen menu to the console.
	 */
	@Override
	public void render(){
//		NumberFormat us = NumberFormat.getCurrencyInstance(Locale.US);
		String prompt;
		isScreenValid = true;
		while(isScreenValid){
			app.getCurrentUser().setAccounts(accountService.getAccountsByUser(app.getCurrentUser()));
			System.out.println("Please choose an option:");
			int i = 0;
			System.out.println((i++ + 1) + ") " + "Create New Account");
			System.out.println("+-----------------------------------------------------+");
//			System.out.println("Test");
			for(int j = 0; j < app.getCurrentUser().getAccounts().size(); j++, i++){
				System.out.println((i+1) + ") " + ((app.getCurrentUser().getAccounts().toArray()[j]).toString()));
			}
			System.out.println("+-----------------------------------------------------+");
			System.out.println((i++ + 1) + ") " + "Return to Dashboard");
			try{
				System.out.print("> ");
				prompt = app.getConsole().readLine().trim();
				if(prompt.equals("exit") || Integer.parseInt(prompt) == i){
					isScreenValid = false;
				} else if (prompt.equals("create") || Integer.parseInt(prompt) == 1){
					app.getRouter().navigate("/newAccount");
				} else if (Integer.parseInt(prompt) < i && Integer.parseInt(prompt) > 0){
					app.setCurrentAccount((Account) app.getCurrentUser().getAccounts().toArray()[Integer.parseInt(prompt)-2]);
					app.getRouter().navigate("/account");
				}
			} catch(NumberFormatException nfe){
				System.out.println("Please enter the number associated with your account.");
				nfe.printStackTrace();
			}
			catch(IOException ioe) {
				System.out.println("Please choose an option");
			    ioe.printStackTrace();
			}
		}

	}
}
