package com.revature.revabank.services;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.*;
import com.revature.revabank.repos.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.revabank.AppDriver.app;

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

	public void authenticate(String username, String password){
		// Validate that the provided username and password are not non-values
		if(username == null || username.trim().equals("") || password == null || password.trim().equals("")){
			throw new InvalidRequestException("Invalid credential values provided");
		}
		AppUser authUser = userRepo.findUserByCredentials(username, password)
				.orElseThrow(AuthenticationException::new);

		app.setCurrentUser(authUser);

//		if(authUser == null){
//			throw new AuthenticationException("No user found with the provided credentials");
//		}
//
//		return authUser;
	}

	public void register(AppUser newUser, String accountType){
		//
		if(!isUserValid(newUser)){
			throw new InvalidRequestException("Invalid user field values provided during registration!");
		}
		Optional<AppUser> existingUser = userRepo.findUserByUsername(newUser.getUserName());

		if(existingUser.isPresent()){
			throw new AuthenticationException("Provided username is already in use!");
		}

		newUser.setRole(Role.PATRON);
		userRepo.save(newUser);

		app.setCurrentUser(newUser);
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
