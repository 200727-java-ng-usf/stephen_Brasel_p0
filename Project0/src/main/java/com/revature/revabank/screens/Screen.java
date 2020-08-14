package com.revature.revabank.screens;

public abstract class Screen {
	String name;
	String route;

	public Screen(){
		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
	}

	public abstract String getRoute();

	/**
	 * Displays a particular menu depending on the screen implementation.
	 */
	public abstract void render();
}
