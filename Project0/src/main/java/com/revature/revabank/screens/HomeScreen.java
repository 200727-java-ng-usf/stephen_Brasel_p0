package com.revature.revabank.screens;

import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeScreen extends Screen{
	//region Fields
	private String name = "HomeScreen";
	private String route = "/home";
	private UserService userService;

	private final String WELCOME = 		"Welcome to RevaBank! Please select an option:";
	private final String BARLINE = 		"=============================================\n";
	private final String SIGN_IN = 		"1. Sign in";
	private final String REGISTER = 	"2. Register new user";
	private final String EXIT = 		"3. Exit";
	//endregion

	//region Constructors

	public HomeScreen(UserService userService) {
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
		System.out.println(WELCOME);
		System.out.println(BARLINE);
		System.out.println(SIGN_IN);
		System.out.println(REGISTER);
		System.out.println(EXIT);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = br.readLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		switch (input) {
			case "1":
				break;
			case "2":
				break;
			case "3":
			case "exit":
				break;
			case "hi":
				System.out.println("Well, hello!");
				break;
			case "Roll Tide!":
				System.out.println("Roll Tide!");
				break;
			default:
				break;
		}
		// TODO clear screen
//			clearConsole();

//			System.out.print("\033[H\033[2J");
//			System.out.flush();

//			try {
//				Runtime.getRuntime().exec("cls");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

//			try {
//				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//endregion
}
