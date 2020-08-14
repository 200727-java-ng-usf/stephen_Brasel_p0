package com.revature.revabank.services;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountChecking;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.repos.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserService {
	private UserRepository userRepo;

	//region Constructors
	public UserService(UserRepository repo){
		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
		userRepo = repo;
	}
	//endregion

	//region Methods
	public Set<AppUser> getAllUser(){
		return null;
	}

	public Set<AppUser> getUsersByRole(Role role){
		return null;
	}

	public Set<AppUser> getUsersById(int id){
		return null;
	}

	public AppUser getUserById(int id){
		return null;
	}

	public AppUser getUserByUsername(String username){
		return null;
	}

	public AppUser authenticate(String username, String password){
		// Validate that the provided username and password are not non-values
		if(username == null || username.trim().equals("") || password == null || password.trim().equals("")){
			// TODO implement a custom invalid request exception.
			throw new InvalidRequestException("Invalid credential values provided");
		}
		AppUser authenticatedUser = userRepo.findUserByCredentials(username, password);

		if(authenticatedUser == null){
			// TODO implement a custom AuthenticationException
			throw new AuthenticationException("No user found with the provided credentials");
		}

		return authenticatedUser;
	}

	public AppUser register(AppUser newUser){
		//
		if(!isUserValid(newUser)){
			//TODO implement a custom InvalidRequestException
			throw new InvalidRequestException("Invalid user field values provided during registration!");
		}
		if(userRepo.findUserByUsername(newUser.getUserName()) != null){
			// TODO implement a custom ResourcePersistenceException
			throw new AuthenticationException("Provided username is already in use!");
		}

		newUser.setRole(Role.PATRON);

		//make a new Account
		Account newAccount = new AccountChecking();
		ArrayList<Account> accounts = new ArrayList<>();
		accounts.add(newAccount);
		newUser.setAccounts(accounts);

		return userRepo.save(newUser);
	}

	public boolean updateUser(AppUser user){
		return false;
	}

	public boolean deleteUserById(int id){
		return false;
	}

	public boolean validateUserFields(AppUser user){
		return false;
	}


	/**
	 * Validates that the given user and its fields are valid (not null or empty strings). Does not
	 * perform validation on id or Role fields.
	 *
	 * @param user
	 * @return true if the user is valid.
	 */
	public boolean isUserValid(AppUser user){

		if(user == null) return false;
		if(user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
		if(user.getLastName() == null || user.getLastName().trim().equals("")) return false;
		if(user.getUserName() == null || user.getUserName().trim().equals("")) return false;
		if(user.getPassword() == null || user.getPassword().trim().equals("")) return false;

		return true;
	}
	//endregion
}
