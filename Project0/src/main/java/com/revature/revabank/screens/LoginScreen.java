package com.revature.revabank.screens;

import com.revature.revabank.models.AppUser;
import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginScreen extends Screen {
	//region Fields
	private String name = "LoginScreen";
	private String route = "/login";
	private UserService userService;
	//endregion

	//region Constructors
	public LoginScreen(UserService userService){
		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
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
		String username;
		String password;

		try{
			System.out.println("Please provide your login credentials");
			System.out.print("Username: ");
			username = console.readLine();
			System.out.print("Password: ");
			password = console.readLine();

//			System.out.println("You entered: " + username + "/" + password);
			AppUser authUser = userService.authenticate(username, password);

			System.out.println(authUser);

			console.close();
		} catch(Exception ioe) {
			ioe.printStackTrace();
		}

		System.out.println();
	}
	//endregion




}
