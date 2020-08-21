package com.revature.revabank;


import com.revature.revabank.repos.UserRepository;
import com.revature.revabank.screens.LoginScreen;
import com.revature.revabank.screens.RegisterScreen;
import com.revature.revabank.services.UserService;
import com.revature.revabank.util.AppState;

public class AppDriver {
	public static AppState app = new AppState();

	public static void main(String[] args) {
		while(app.isAppRunning()){
			System.out.println("Start of while loop");
			app.getRouter().navigate("/home");
			System.out.println("End of while loop");
		}
//		UserRepository userRepo = new UserRepository();
//		UserService userService = new UserService(userRepo);
//
//		RegisterScreen registerScreen = new RegisterScreen(userService);
//		registerScreen.render();
//
//		LoginScreen loginScreen = new LoginScreen(userService);
//		loginScreen.render();
	}

}
