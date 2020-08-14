package com.revature.revabank.repos;

import com.revature.revabank.models.Account;

import java.util.Set;

public class AccountRepository implements CrudRepository<Account> {

	@Override
	public Account save(Account account) {
		return null;
	}

	@Override
	public Set<Account> findAll() {
		return null;
	}

	@Override
	public Account findById(String id) {
		return null;
	}

	@Override
	public boolean update(Account account) {
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}
}
