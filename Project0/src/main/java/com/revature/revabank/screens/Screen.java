package com.revature.revabank.screens;

public abstract class Screen {
	private String name;
	private String route;
	protected boolean isScreenValid = true;

	public Screen(String name, String route){
//		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
		this.name = name;
		this.route = route;
	}

	public String getName() {
		return name;
	}

	public String getRoute() {
		return route;
	}

	/**
	 * Displays a particular menu depending on the screen implementation.
	 */
	public abstract void render();
}
