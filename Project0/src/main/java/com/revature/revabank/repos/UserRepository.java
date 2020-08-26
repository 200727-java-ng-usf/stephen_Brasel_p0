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
	private String baseQuery =
			"SELECT * FROM revabank.app_users au " +
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
//		return new HashSet<>();
		Set<AppUser> _user = new HashSet<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = baseQuery + "WHERE role_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Role.getOrdinal(role));

			ResultSet rs =  pstmt.executeQuery();
			_user = mapResultSet(rs);


		} catch(SQLException sqle){
			sqle.printStackTrace();
		}

		return _user;
	}

	public Optional<AppUser> findUserByUsername(String username){
//		return userDataset.findUserByUsername(username);
//		return Optional.of(null);
		Optional<AppUser> _user = Optional.empty();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = baseQuery + "WHERE username = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);

			ResultSet rs =  pstmt.executeQuery();
			_user = mapResultSet(rs).stream().findFirst();

		} catch(SQLException sqle){
			sqle.printStackTrace();
		}

		return _user;
	}

	public Optional<AppUser> findUserByCredentials(String username, String password){

		Optional<AppUser> _user = Optional.empty();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = baseQuery
					+ "WHERE username = ? AND user_password = ? "
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
			temp.setFirstName(rs.getString("first_name"));
			temp.setLastName(rs.getString("last_name"));
			temp.setUserName(rs.getString("username"));
			temp.setPassword(rs.getString("user_password"));
			temp.setEmail(rs.getString("email"));
			temp.setRole(Role.getByName(rs.getString("role_name")));
//			System.out.println(temp);

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
					"INSERT INTO revabank.app_users " +
							"(" +
							"first_name" +
							", last_name" +
							", username" +
							", user_password" +
							", email" +
							", role_id) " +
							//(username, password, first_name, last_name, email, role_id)
							"VALUES (?, ?, ?, ?, ?, ?) "
//					"VALUES (0, ?, ?, ?, ?, ?, ?) "
					;
			// second parameter here is used to indicate column names that will have generated values
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
			pstmt.setString(1, newUser.getFirstName());
			pstmt.setString(2, newUser.getLastName());
			pstmt.setString(3, newUser.getUserName());
			pstmt.setString(4, newUser.getPassword());
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
//		return new HashSet<>();
		Set<AppUser> _user = new HashSet<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			PreparedStatement pstmt = conn.prepareStatement(baseQuery);

			ResultSet rs =  pstmt.executeQuery();
			_user = mapResultSet(rs);

		} catch(SQLException sqle){
			sqle.printStackTrace();
		}

		return _user;
	}

	@Override
	public Optional<AppUser> findById(int id){
//		return new HashSet<>();
		Optional<AppUser> _user = Optional.empty();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = baseQuery + "WHERE id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs =  pstmt.executeQuery();
			_user = mapResultSet(rs).stream().findFirst();

		} catch(SQLException sqle){
			sqle.printStackTrace();
		}

		return _user;
	}

	@Override
	public boolean update(AppUser user){
		return false;
	}

	@Override
	public boolean deleteById(int id){
		Optional<AppUser> _user = Optional.empty();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "delete from revabank.app_users au "
					+ "WHERE id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs =  pstmt.executeQuery();
			_user = mapResultSet(rs).stream().findFirst();

		} catch(SQLException sqle){
			sqle.printStackTrace();
			return false;
		}

		return true;
	}

	public Set<AppUser> findUsersByAccountId(int id) {
//		return new HashSet<>();
		Set<AppUser> _user = new HashSet<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT * FROM revabank.account_users au " +
					"FULL OUTER JOIN revabank.app_users apusr " +
					"ON au.account_user_id = apusr.id " +
					"JOIN revabank.user_roles ur " +
					"ON apusr.role_id = ur.id " +
					"WHERE au.account_user_id = ? ";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs =  pstmt.executeQuery();
			_user = mapResultSet(rs);


		} catch(SQLException sqle){
			sqle.printStackTrace();
		}

		return _user;
	}
	//endregion
}
