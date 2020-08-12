package com.revature.models;

import java.util.ArrayList;

public class AppUser {
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private Role role;
	private ArrayList<Account> accounts;
}
