package com.revature.revabank.util;



import com.revature.revabank.models.Account;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.repos.AccountRepository;
import com.revature.revabank.repos.UserRepository;
import com.revature.revabank.screens.*;
import com.revature.revabank.services.AccountService;
import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {
	private BufferedReader console;
	private AppUser currentUser;
	private Account currentAccount;
	private ScreenRouter router;
	private boolean appRunning;
	private static boolean debug = true;

	public AppState(){
//		System.out.println("[LOG] - Initializing Application...");

		appRunning = true;
		console = new BufferedReader(new InputStreamReader(System.in));

		final UserRepository userRepo = new UserRepository();
		final AccountRepository accountRepo = new AccountRepository(userRepo);
		final UserService userService = new UserService(userRepo);
		final AccountService accountService = new AccountService(accountRepo);

		router = new ScreenRouter();

		//THESE ONLY NEED THE SERVICES, NEVER THE REPOS.
		router.addScreen(new HomeScreen())
				.addScreen(new DashboardScreen())
				.addScreen(new AccountManagerScreen(accountService))
				.addScreen(new AccountScreen(accountService))
				.addScreen(new NewAccountScreen(accountService))
				.addScreen(new TransferScreen())
				.addScreen(new UserProfileScreen())
				.addScreen(new LoginScreen(userService))
				.addScreen(new RegisterScreen(userService, accountService))
		;

//		System.out.println("[LOG] - Application Initialization complete.");
	}

	public BufferedReader getConsole() {
		return console;
	}

	public Account getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(Account currentAccount) {
		this.currentAccount = currentAccount;
	}

	public AppUser getCurrentUser() {
		return currentUser;
	}

	public ScreenRouter getRouter() {
		return router;
	}

	public boolean isAppRunning() {
		return appRunning;
	}

	public void setAppRunning(boolean appRunning) {
		this.appRunning = appRunning;
	}

	public static boolean isDebug() {
		return debug;
	}

	public void invalidateCurrentSession(){
		currentUser = null;
	}

	public boolean isSessionValid(){
		return (this.currentUser != null);
	}

	public void setCurrentUser(AppUser newUser) {
		currentUser = newUser;
	}
}
