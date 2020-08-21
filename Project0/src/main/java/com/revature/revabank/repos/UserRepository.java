package com.revature.revabank.repos;

import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserRepository implements CrudRepository<AppUser>{

	//region Fields
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

	public Optional<AppUser> findUserByUsername(String username){
//		return userDataset.findUserByUsername(username);
		return Optional.of(null);
	}

	public Optional<AppUser> findUserByCredentials(String username, String password){

		Optional<AppUser> _user = Optional.empty();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT * FROM revabooks.app_users WHERE username = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs =  pstmt.executeQuery();

			AppUser appUser = new AppUser();

			while(rs.next()){
				appUser.setId(rs.getInt("id"));
				appUser.setUserName(rs.getString("username"));
				appUser.setPassword(rs.getString("password"));
				appUser.setFirstName(rs.getString("first_name"));
				appUser.setLastName(rs.getString("last_name"));
//				appUser.setEmail(rs.getString("email"));
//				appUser.setRole(rs.getString("role_id"));
			}

			_user = Optional.of(appUser);

		} catch(SQLException sqle){
			sqle.printStackTrace();
		}


		return _user;

	}
	//endregion

	//region Overridden Methods
	@Override
	public Optional<AppUser> save(AppUser user){
		return Optional.of(null);
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
