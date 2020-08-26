package com.revature.revabank.repos;

import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.services.UserService;
import com.revature.revabank.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.revabank.AppDriver.app;

public class AccountRepository implements CrudRepository<Account> {

	//region Fields
	// Extract common query clauses into a easily referenced thingamabobamagigit
	/**
	 * "SELECT * FROM revabank.accounts a " +
	 * 			"JOIN revabank.user_roles ur " +
	 * 			"ON a.role_id = ur.id "
	 */
	private String baseQuery = "SELECT * FROM revabank.accounts ac " +
			"JOIN revabank.account_users au " +
			"ON au.id = ur.id ";
	private UserRepository userRepository;
	//endregion
	public AccountRepository(UserRepository userRepository) {
		System.out.println("[LOG] - Instantiating " + this.getClass().getName());
		this.userRepository = userRepository;
	}

	//region Methods
	private Set<Account> mapResultSet(ResultSet rs) throws SQLException {
		Set<Account> users = new HashSet<>();

		while (rs.next()) {
			Account temp = new Account();

			temp.setId(rs.getInt("id"));
			temp.setOwners(
					userRepository.findUsersByAccountId(rs.getInt("account_user_id")));
			temp.setName(rs.getString("account_name"));
			temp.setBalance(rs.getDouble("balance"));
			//TODO Transactions
//			temp.setHistory(rs.getString("last_name"));
			temp.setType(AccountType.getByName(rs.getString("account_type")));

			users.add(temp);
		}

		return users;
	}

	public Set<Account> findAccountsByUserId(int id) {
		Set<Account> _accounts = new HashSet<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT a.id, a.account_name, a.balance, " +
					"acu.account_id, acu.account_user_id, " +
					"at.account_type " +
					"FROM revabank.accounts a " +
					"JOIN revabank.account_users acu " +
					"ON a.id = acu.account_id " +
					"JOIN revabank.account_types at " +
					"ON a.account_type = at.id " +
					"WHERE acu.account_user_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs =  pstmt.executeQuery();
			_accounts = mapResultSet(rs);

		} catch(SQLException sqle){
			System.out.println("No Accounts found for user#" + id + ".");
			sqle.printStackTrace();
		}

		return _accounts;
	}

	private boolean save(int account_id, int account_user_id){
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql =
					"INSERT INTO revabank.account_users " +
							"(" +
							"account_id, " +
							"account_user_id) " +
							"VALUES (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
			pstmt.setInt(		1,	account_id);
			pstmt.setInt(		2,	account_user_id);

			int rowsInserted = pstmt.executeUpdate();
			if(rowsInserted > 0){
				return true;
			}
		}catch(SQLException sqle){
			System.out.println("Failed to save account user information.");
//			sqle.printStackTrace();
		}
		return false;
	}
	//endregion

	//region Overridden Methods
	@Override
	public void save(Account account) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql =
					"INSERT INTO revabank.accounts " +
							"(" +
							"account_name " +
							", balance " +
							", account_type) " +
							"VALUES (?, ?, ?) "
					;
			// second parameter here is used to indicate column names that will have generated values
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
			pstmt.setString(		1,	account.getName());
			pstmt.setDouble(		2,	account.getBalance());
			pstmt.setInt(			3,	AccountType.getOrdinal(account.getType()));

			int rowsInserted = pstmt.executeUpdate();

			if(rowsInserted > 0){
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				account.setId(rs.getInt("id"));
				save(account.getId(), app.getCurrentUser().getId());
//				account.setId(rs.getInt(1)); // also works
			}

		} catch(SQLException sqle){
			System.out.println("Failed to save account.");
//			sqle.printStackTrace();
		}
	}

	@Override
	public Set<Account> findAll() {
		Set<Account> _accounts = new HashSet<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			PreparedStatement pstmt = conn.prepareStatement(baseQuery);

			ResultSet rs =  pstmt.executeQuery();
			_accounts = mapResultSet(rs);

		} catch(SQLException sqle){
			System.out.println("Failed to find accounts.");
//			sqle.printStackTrace();
		}

		return _accounts;
	}

	@Override
	public Optional<Account> findById(int id) {
		return null;
	}

	@Override
	public boolean update(Account account) {
		return false;
	}

	public boolean update(Double amount){
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql =
					"UPDATE revabank.accounts " +
							"SET balance = balance + ? " +
							"WHERE id = ?"
					;
			// second parameter here is used to indicate column names that will have generated values
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
			pstmt.setDouble(	1,	amount);
			pstmt.setInt(			2,	app.getCurrentAccount().getId());

			int rowsInserted = pstmt.executeUpdate();

			if(rowsInserted != 0){
				return true;
			}

		} catch(SQLException sqle){
			System.out.println("Failed to update account.");
//			sqle.printStackTrace();
		}
		return false;

	}
	@Override
	public boolean deleteById(int id) {
		return false;
	}
	//endregion
}
