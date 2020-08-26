package com.revature.revabank.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTypeTest {

	//region positive tests
	@Test
	public void getByName() {
		AccountType expected = AccountType.CHECKING;
		AccountType actual = AccountType.getByName("Checking");
		assertEquals(expected, actual);
	}

	@Test
	public void getOrdinal() {
		int expected = 2;
		int actual = AccountType.getOrdinal(AccountType.CHECKING);
		assertEquals(expected, actual);
	}

	@Test
	public void testToString() {
		String expected = "Checking";
		String actual = (AccountType.CHECKING).toString();
		assertEquals(expected, actual);
	}
	//endregion

	//region negative tests
	@Test
	public void testGetNameUnknown(){
		AccountType expected = AccountType.CHECKING;
		AccountType actual = AccountType.getByName("I can haz chezburger?");
		assertEquals(expected, actual);
	}
	@Test
	public void getOrdinalUnkown() {
		int expected = 2;
		int actual = AccountType.getOrdinal(null);
		assertEquals(expected, actual);
	}
	//endregion
}