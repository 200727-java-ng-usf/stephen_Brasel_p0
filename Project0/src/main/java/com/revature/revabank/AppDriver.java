package com.revature.revabank;


import com.revature.revabank.repos.UserRepository;
import com.revature.revabank.screens.LoginScreen;
import com.revature.revabank.screens.RegisterScreen;
import com.revature.revabank.services.UserService;

public class AppDriver {
	private static boolean debug = true;

	public static boolean isDebug() {
		return debug;
	}

	public static void setDebug(boolean debug) {
		AppDriver.debug = debug;
	}

	public static void main(String[] args) {
		UserRepository userRepo = new UserRepository();
		UserService userService = new UserService(userRepo);

		RegisterScreen registerScreen = new RegisterScreen(userService);
		registerScreen.render();

		LoginScreen loginScreen = new LoginScreen(userService);
		loginScreen.render();
	}

}
