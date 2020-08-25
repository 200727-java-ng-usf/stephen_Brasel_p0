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
	// Extract common query clauses into a easily referenced thingamabobamagigit
	/**
	 * "SELECT * FROM revabank.app_users au " +
	 * 			"JOIN revabank.user_roles ur " +
	 * 			"ON au.role_id = ur.id "
	 */
	private String baseQuery = "SELECT * FROM revabank.app_users au " +
			"JOIN revabank.user_roles ur " +
			"ON au.role_id = ur.id ";
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

			String sql = baseQuery
					+ "WHERE username = ? AND password = ? "
					;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs =  pstmt.executeQuery();

			_user = mapResultSet(rs).stream().findFirst();

		} catch(SQLException sqle){
			sqle.printStackTrace();
		}


		return _user;

	}

	private Set<AppUser> mapResultSet(ResultSet rs) throws SQLException {
		Set<AppUser> users = new HashSet<>();

		while (rs.next()) {
			AppUser temp = new AppUser();

			temp.setId(rs.getInt("id"));
			temp.setUserName(rs.getString("username"));
			temp.setPassword(rs.getString("password"));
			temp.setFirstName(rs.getString("first_name"));
			temp.setLastName(rs.getString("last_name"));
//				temp.setEmail(rs.getString("email"));
			// TODO figure out how to set the Role of an AppUser using a ResultSet
			temp.setRole(Role.getByName(rs.getString("name")));
			System.out.println(temp);

			users.add(temp);
		}

		return users;
	}
	//endregion

	//region Overridden Methods
	@Override
	public void save(AppUser newUser){
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql =
					"INSERT INTO revabooks.app_users " +
							"(username, password, first_name, last_name, email, role_id) " +
							//(username, password, first_name, last_name, email, role_id)
							"VALUES (?, ?, ?, ?, ?, ?) "
//					"VALUES (0, ?, ?, ?, ?, ?, ?) "
					;
			// second parameter here is used to indicate column names that will have generated values
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
			pstmt.setString(1, newUser.getUserName());
			pstmt.setString(2, newUser.getPassword());
			pstmt.setString(3, newUser.getFirstName());
			pstmt.setString(4, newUser.getLastName());
			pstmt.setString(5, newUser.getEmail());
//			pstmt.setString(5, newUser.getFirstName().toLowerCase().charAt(0)
//					+ newUser.getLastName().toLowerCase()
//					+ "@revature.com");
			pstmt.setInt(6, newUser.getRole().ordinal() + 1);
//			pstmt.setInt(6, Role.getOrdinal(newUser.getRole()));

			int rowsInserted = pstmt.executeUpdate();

			if(rowsInserted != 0){

				ResultSet rs = pstmt.getGeneratedKeys();

				rs.next();
				newUser.setId(rs.getInt(1));
//					newUser.setId(rs.getInt("id")); // also works
			}

		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
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
