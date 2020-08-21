package com.revature.revabank.screens;

import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.revature.revabank.AppDriver.app;

public class HomeScreen extends Screen{
	//region Constructors
	public HomeScreen() {
		super("HomeScreen", "/home");
		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
	}

	//endregion

	//region Overridden Methods
	@Override
	public void render() {
		System.out.println(	"Welcome to RevaBank! Please select an option:\n" +
							"=============================================\n" +
							"1. Login\n" +
							"2. Register\n" +
							"3. Exit Application\n" +
							"> ");
		String input = "";
		try {
			input = app.getConsole().readLine().trim();
			switch (input.toLowerCase()) {
				case "1": case "login":
					app.getRouter().navigate("/login");
					break;
				case "2": case "register":
					app.getRouter().navigate("/register");
					break;
				case "3": case "exit":
					app.setAppRunning(false);
					break;
				case "hi": case "hello":
					System.out.println("Well, hello!");
					break;
				case "roll tide!":
					System.out.println("Roll Tide!");
					break;
				default:
					System.out.println("[LOG] - Invalid Selection!");
					break;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	//endregion
}
