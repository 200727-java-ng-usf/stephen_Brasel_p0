package com.revature.revabank.repos;

import com.revature.revabank.db.UserDB;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;

import java.util.HashSet;
import java.util.Set;

import static com.revature.revabank.db.UserDB.userDataset;

public class UserRepository implements CrudRepository<AppUser>{

	//region Fields
	private UserDB userDataBase = userDataset;
	//endregion

	//region Constructors
	public UserRepository(){
		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
	}
	//endregion

	//region Methods
	public Set<AppUser> findUsersByRole(Role role){
		return new HashSet<>();
	}

	public AppUser findUserByUsername(String username){
		return userDataset.findUserByUsername(username);
	}

	public AppUser findUserByCredentials(String username, String pw){
		return userDataset.findUserByCredentials(username, pw);

	}
	//endregion

	//region Overridden Methods
	@Override
	public AppUser save(AppUser user){
		return userDataset.addUser(user);
	}

	@Override
	public Set<AppUser> findAll(){
		return new HashSet<>();
	}

	@Override
	public AppUser findById(String id){
		return null;
	}

	@Override
	public boolean update(AppUser user){
		return false;
	}

	@Override
	public boolean deleteById(String id){
		return true;
	}
	//endregion
}
