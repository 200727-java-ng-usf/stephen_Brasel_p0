package com.revature.revabank.screens;

import com.revature.revabank.models.Account;

import java.io.IOException;

import static com.revature.revabank.AppDriver.app;

public class AccountScreen extends Screen{

	public AccountScreen(){
		super("AccountScreen", "/account");

	}

	/**
	 * Renders the login screen menu to the console.
	 */
	@Override
	public void render(){
		for(Account acc :app.getCurrentUser().getAccounts()){
			System.out.println(acc);
		}
//		String prompt;
//
//		while(app.isSessionValid()){
//			System.out.println("Welcome to your Dashboard!\n");
//			System.out.println("1) Search Books");
//			System.out.println("2) View Profile");
//			System.out.println("3) Logout");
//			try {
//				System.out.print("> ");
//				prompt = app.getConsole().readLine().trim();
//
//				switch (prompt) {
//					case "1":
//						app.getRouter().navigate("/search");
//						break;
//					case "2":
//						app.getRouter().navigate("/profile");
//						break;
//					case "3":
//						app.invalidateCurrentSession();
//						break;
//					default:
//						System.out.println("[LOG] - Invalid Selection!");
//						break;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
	}
}
