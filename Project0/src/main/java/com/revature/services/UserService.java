package com.revature.services;

import com.revature.models.AppUser;
import com.revature.models.Role;
import com.revature.repos.UserRepository;

import java.util.Set;

public class UserService {
	private UserRepository userRepository;

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

	public AppUser getUserByUserName(String username){
		return null;
	}

	public AppUser authenticate(String username, String password){
		return null;
	}

	public AppUser register(AppUser user){
		return null;
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
}
