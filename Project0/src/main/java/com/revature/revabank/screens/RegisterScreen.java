package com.revature.revabank.screens;

import com.revature.revabank.models.AppUser;
import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RegisterScreen extends Screen{
	//region Fields
	private String name = "RegisterScreen";
	private String route = "/register";
	private UserService userService;
	//endregion

	//region Constructors

	public RegisterScreen(UserService userService) {
//		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
		this.userService = userService;
	}

	//endregion

	//region Overridden Methods
	@Override
	public String getRoute() {
		return null;
	}

	@Override
	public void render() {

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String accountType;
		String firstName;
		String lastName;
		String userName;
		String password;

		try{
			System.out.println("Sign up for a new account!");
			System.out.print("First name: ");
			firstName = console.readLine();
			System.out.print("Last name: ");
			lastName = console.readLine();
			System.out.print("Username: ");
			userName = console.readLine();
			System.out.print("Password: ");
			password = console.readLine();
			System.out.print("Account Type: ");
			accountType = console.readLine();

			AppUser newUser = new AppUser(firstName, lastName, userName, password);

			AppUser registeredUser = userService.register(newUser);
			System.out.println(registeredUser);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	//endregion
}
