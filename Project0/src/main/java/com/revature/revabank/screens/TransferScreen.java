package com.revature.revabank.screens;

import static com.revature.revabank.AppDriver.app;

public class TransferScreen extends Screen {

	public TransferScreen() {
		super("TransferScreen", "/transfer");
	}

	@Override
	public void render() {
		System.out.println(app.getCurrentUser());
		System.out.println("The transfer screen works!");
	}
}
