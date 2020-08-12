package com.revature.screens;

public abstract class Screen {
	String name;
	String route;

	public abstract String getRoute();
	public abstract void render();
}
