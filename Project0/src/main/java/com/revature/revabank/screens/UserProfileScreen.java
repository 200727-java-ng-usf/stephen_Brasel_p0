package com.revature.revabank.screens;

import static com.revature.revabank.AppDriver.app;

public class UserProfileScreen extends Screen{

	public UserProfileScreen() {
		super("UserProfileScreen", "/profile");
	}

	@Override
	public void render() {
		System.out.println(app.getCurrentUser());
	}
}
