package com.revature.revabank.models;

import java.util.ArrayList;
import java.util.Objects;

public class AppUser {
	//region Fields
	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private transient String password;
	private Role role;
	private ArrayList<Account> accounts;
	//endregion

	//region Constructors
	public AppUser(){
		super();
	}

	public AppUser(String firstName, String lastName, String userName, String password) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
	public AppUser(String firstName, String lastName, String userName, String password, ArrayList<Account> accounts) {
		this(firstName, lastName, userName, password);
		this.role = Role.PATRON;
	}
	public AppUser(String firstName, String lastName, String userName, String password, Role role) {
		this(firstName, lastName, userName, password);
		this.role = role;
	}
	public AppUser(String id, String firstName, String lastName, String userName, String password, Role role, ArrayList<Account> accounts) {
		this(firstName, lastName, userName, password, role);
		this.id = id;
		this.accounts = accounts;
	}

	public AppUser(AppUser user){
		this(user.id, user.firstName, user.lastName, user.userName, user.password, user.role, user.accounts);
	}
	//endregion

	//region Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getString() {
		return password;
	}

	public void setString(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	//endregion

	//region Methods
	//endregion

	//region OverRidden Methods

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AppUser appUser = (AppUser) o;
		return id == appUser.id &&
				Objects.equals(firstName, appUser.firstName) &&
				Objects.equals(lastName, appUser.lastName) &&
				Objects.equals(userName, appUser.userName) &&
				Objects.equals(password, appUser.password) &&
				role == appUser.role &&
				Objects.equals(accounts, appUser.accounts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, userName, password, role, accounts);
	}

	@Override
	public String toString() {
		return "AppUser{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				", accounts=" + accounts +
				'}';
	}

	//endregion
}
