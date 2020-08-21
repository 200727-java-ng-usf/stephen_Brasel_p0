package com.revature.revabank.util;


import com.revature.revabank.screens.Screen;

import java.util.HashSet;
import java.util.Set;

public class ScreenRouter {
	private Set<Screen> screens = new HashSet<>();

	public Set<Screen> getScreens() {
		return screens;
	}

	public ScreenRouter addScreen(Screen screen){
		System.out.println("[LOG] - Loading " + screen.getName() + " into the router");
		screens.add(screen);
		return this;
	}

	public void navigate(String route){
		for (Screen screen : screens) {
			String routeStr = screen.getRoute();
			if(routeStr == null)
				System.out.print(screen.getClass().getName() + ": ");
			System.out.println(routeStr);
			boolean isRoute = routeStr.equals(route);
			if(isRoute) {
				screen.render();
				return;
			}
		}

		throw new RuntimeException("No screen found with that route.");
//		screens.stream()
//				.filter(screen -> screen.getRoute().equals(route))
//				.findFirst()
//				.orElseThrow(() -> new RuntimeException("No screen found with that route."))
//				.render();
	}
}
