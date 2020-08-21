package com.revature.revabank.screens;

import com.revature.revabank.models.AppUser;
import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.revature.revabank.AppDriver.app;

public class LoginScreen extends Screen {
	//region Fields
	private UserService userService;
	//endregion

	//region Constructors
	public LoginScreen(UserService userService){
		super("LoginScreen", "/login");
//		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
		this.userService = userService;
	}
	//endregion

	//region Overridden Methods

	@Override
	public void render() {
		String username, password;

		try{
			System.out.println("Please provide your login credentials");
			System.out.print("Username: ");
			username = app.getConsole().readLine().trim();
			System.out.print("Password: ");
			password = app.getConsole().readLine().trim();

//			System.out.println("You entered: " + username + "/" + password);
			userService.authenticate(username, password);

			if(app.isSessionValid()){
				app.getRouter().navigate("/dashboard");
			}
		} catch(Exception ioe) {
			ioe.printStackTrace();
		}

		System.out.println();
	}
	//endregion




}
