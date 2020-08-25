package com.revature.revabank.screens;

import com.revature.revabank.models.AppUser;
import com.revature.revabank.services.AccountService;
import com.revature.revabank.services.UserService;

import static com.revature.revabank.AppDriver.app;

public class RegisterScreen extends Screen{
	//region Fields
	private UserService userService;
	private AccountService accountService;
	//endregion

	//region Constructors

	public RegisterScreen(UserService userService, AccountService accountService) {
		super("RegisterScreen", "/register");
//		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
		this.userService = userService;
		this.accountService = accountService;
	}

	//endregion

	//region Overridden Methods
	@Override
	public void render() {

		String accountType, firstName, lastName, userName, password, email;

		try{
			System.out.println("Sign up for a new account!");
			System.out.print("First name: ");
			firstName = app.getConsole().readLine().trim();
			System.out.print("Last name: ");
			lastName = app.getConsole().readLine().trim();
			System.out.print("Username: ");
			userName = app.getConsole().readLine().trim();
			System.out.print("Password: ");
			password = app.getConsole().readLine().trim();
			System.out.print("Email: ");
			email = app.getConsole().readLine().trim();
			System.out.print("Account Type: \n"
							+"1) Checking\n"
							+"2) Savings\n"
			);
			accountType = app.getConsole().readLine().trim();

			AppUser newUser = new AppUser(firstName, lastName, userName, password, email);

			userService.register(newUser, accountType);

			if(app.isSessionValid()){
				accountService.addAccount(accountType);
				app.getRouter().navigate("/dashboard");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	//endregion
}
