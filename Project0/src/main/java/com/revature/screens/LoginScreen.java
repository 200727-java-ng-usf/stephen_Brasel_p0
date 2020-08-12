package com.revature.screens;

import com.revature.AppDriver;
import com.revature.models.AppUser;
import com.revature.services.UserService;

public class LoginScreen extends Screen{
	private String name = "LoginScreen";
	private String route = "/login";
	private UserService userService;


	public static final String WELCOME = 	"Welcome to RevaBank! Please select an option:";
	public static final String BARLINE = 	"=============================================\n";
	public static final String SIGN_IN = 	"1. Sign in";
	public static final String REGISTER = 	"2. Register new user";
	public static final String EXIT = 		"3. Exit";

	public LoginScreen() {
		userService = AppDriver.getUserService();
	}

	@Override
	public String getRoute() {
		return null;
	}

	@Override
	public void render() {
		System.out.println(WELCOME);
		System.out.println(BARLINE);
		System.out.println(SIGN_IN);
		System.out.println(REGISTER);
		System.out.println(EXIT);

	}
}
