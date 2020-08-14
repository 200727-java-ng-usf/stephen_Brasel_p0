package com.revature.revabank.db;

import com.revature.revabank.models.Account;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;

import java.util.HashMap;

public class UserDB extends HashMap<Integer, AppUser> {


	public static UserDB userDataset = new UserDB();
	public static int key = 1;

	static {
		userDataset.addUser(new AppUser("Adam", "Inn", "admin", "p4ssw0rd", Role.ADMIN));
		userDataset.addUser(new AppUser("Wezley", "Singleton", "wsingleton", "Java", Role.PATRON));
		userDataset.addUser(new AppUser("Stephen", "Brasel", "SBrasel", "FIRE", Role.PATRON));
		userDataset.addUser(new AppUser("Blake", "Kruppa", "bkruppa", "javascript", Role.PATRON));
		userDataset.addUser(new AppUser("Dylan", "McBee", "dmcbee", "password", Role.PATRON));
		userDataset.addUser(new AppUser("Nickolas", "Jurczak", "njurczak", "drowssap", Role.PATRON));
	}

	public AppUser addUser(AppUser newUser) {
//		System.out.println(key);
		AppUser copy = new AppUser(newUser);
		copy.setId(String.valueOf(key));
		userDataset.put(key++, copy);
		return copy;
	}

	public AppUser findUserByCredentials(String username, String password) {

		return userDataset.values()
				.stream()
				.filter(user ->
						user.getUserName().equals(username) && user.getPassword().equals(password))
				.findFirst()
				.orElse(null);

//		for (AppUser user :userDataset.values()){
//			if(user.getUserName().equals(username) && user.getPassword().equals(password)){
//				return user;
//			}
//		}
//		return null;
	}

	public AppUser findUserByUsername(String username) {

		// using the Stream API (all Collection implementations have a .stream() method)
		return userDataset.values()
				.stream()
				.filter(user -> user.getUserName().equals(username))
				.findFirst()
				.orElse(null);

//		for (AppUser user :userDataset.values()){
//			if(user.getUserName().equals(username)){
//				return user;
//			}
//		}
//		return null;
	}
}
