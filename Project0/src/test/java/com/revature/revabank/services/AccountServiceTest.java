package com.revature.revabank.services;

import com.revature.revabank.models.AppUser;
import com.revature.revabank.repos.AccountRepository;
import com.revature.revabank.repos.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AccountServiceTest {

	AccountRepository moccount;
	AppUser mockapp;
	AccountService sut;

	@Before
	public void setUp() throws Exception {
		moccount = Mockito.mock(AccountRepository.class);
		mockapp = Mockito.mock(AppUser.class);
		sut = new AccountService(moccount);
	}

	@After
	public void tearDown() throws Exception {
		sut = null;
	}

	@Test
	public void nullCheck(){
		assertNotNull(sut);
	}

	@Test
	public void getAllAccounts() {
		assertNull(sut.getAllAccounts());
	}

	@Test
	public void getAccountsByUser() {
		Set<AppUser> users = new HashSet<>();
		sut.getAccountsByUser(mockapp);
	}

	@Test
	public void getAccountById() {
	}

	@Test
	public void testGetAllAccounts() {
	}

	@Test
	public void testGetAccountsByUser() {
	}

	@Test
	public void testGetAccountById() {
	}

	@Test
	public void getAccountByAccountName() {
	}

	@Test
	public void deposit() {
	}

	@Test
	public void withdraw() {
	}

	@Test
	public void addAccount() {
	}
}