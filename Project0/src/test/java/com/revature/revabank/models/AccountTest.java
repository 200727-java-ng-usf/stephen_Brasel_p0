package com.revature.revabank.models;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class AccountTest {
	Account sup;

	@Before
	public void setUp() throws Exception {
		sup = new AccountChecking();
	}

	@After
	public void tearDown() throws Exception {
	}
}