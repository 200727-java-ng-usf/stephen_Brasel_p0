package com.revature.revabank.services;

import com.revature.revabank.exceptions.NegativeTransactionException;
import com.revature.revabank.exceptions.OverflowTransactionException;
import com.revature.revabank.exceptions.TransactionException;
import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.repos.AccountRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.revabank.AppDriver.app;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

	AccountRepository moccountRepo;
	AppUser mockapp;
	Account maccount1;
	Account maccount2;
	Account maccount3;
	AccountService sut;

	@Before
	public void setUp() throws Exception {
		moccountRepo = Mockito.mock(AccountRepository.class);
		mockapp = Mockito.mock(AppUser.class);
		maccount1 = Mockito.mock(Account.class);
		maccount2 = Mockito.mock(Account.class);
		maccount3 = Mockito.mock(Account.class);
		sut = new AccountService(moccountRepo);
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
		Set<Account> accounts = new HashSet<>();
		accounts.add(maccount1);
		when(moccountRepo.findAccountsByUserId(anyInt())).thenReturn(accounts);
		assertEquals(Optional.of(maccount1), sut.getAccountById(1));
	}

	@Test
	public void testGetAllAccounts() {
		assertNull(sut.getAllAccounts());
	}

	@Test
	public void testGetAccountsByUser() {
		Set<Account> accounts = new HashSet<>();
		accounts.add(maccount1);
		accounts.add(maccount2);
		accounts.add(maccount3);
		when(moccountRepo.findAccountsByUserId(anyInt())).thenReturn(accounts);

		assertEquals(accounts, sut.getAccountsByUser(mockapp));
	}

	@Test
	public void testGetAccountById() {
		Set<Account> accounts = new HashSet<>();
		accounts.add(maccount1);
		when(moccountRepo.findAccountsByUserId(anyInt())).thenReturn(accounts);
		assertEquals(Optional.of(maccount1), sut.getAccountById(mockapp.getId()));
	}

	@Test
	public void getAccountByAccountName() {
		assertNull(sut.getAccountByAccountName(""));
	}

	@Test(expected = NegativeTransactionException.class)
	public void depositNegative() {
		sut.deposit(-Double.MAX_VALUE);
	}

	@Test(expected = OverflowTransactionException.class)
	public void depositInfinite() {
		maccount1.setBalance(0D);
		app.setCurrentAccount(maccount1);
		sut.deposit(Double.POSITIVE_INFINITY);
	}

	@Test
	public void deposit() {
		Double expected = 40D;
		maccount1.setBalance(expected);
		app.setCurrentAccount(maccount1);
		when(moccountRepo.update(anyDouble())).thenReturn(true);
		assertEquals(expected, sut.deposit(expected));
	}

	@Test
	public void depositRepoFailure() {
		Double expected = 40D;
		maccount1.setBalance(expected);
		app.setCurrentAccount(maccount1);
		when(moccountRepo.update(anyDouble())).thenReturn(false);
		assertEquals((Double)0D, sut.deposit(expected));
	}

	@Test(expected = NegativeTransactionException.class)
	public void withdrawNegative() {
		sut.withdraw(-Double.MAX_VALUE);
	}

	@Test(expected = TransactionException.class)
	public void withdrawOverdraft() {
		maccount1.setBalance(0D);
		app.setCurrentAccount(maccount1);
		sut.withdraw(Double.MIN_VALUE);
	}

	@Test
	public void withdraw() {
		Double expected = 40D;
		when(maccount1.getBalance()).thenReturn(expected*2);
		app.setCurrentAccount(maccount1);
		when(moccountRepo.update(anyDouble())).thenReturn(true);
		assertEquals(expected, sut.withdraw(expected));
	}

	@Test
	public void withdrawRepoFailure() {
		Double expected = 40D;
		when(maccount1.getBalance()).thenReturn(expected*2);
		app.setCurrentAccount(maccount1);
		when(moccountRepo.update(anyDouble())).thenReturn(false);
		assertEquals((Double)0D, sut.withdraw(expected));
	}

	@Test(expected = TransactionException.class)
	public void addAccountStartingBalanceTooLow() {
		sut.addAccount("0", "", "");
	}

	@Test
	public void addAccountTypeTest() {
		app.setCurrentUser(mockapp);
		sut.addAccount(sut.getStartingValueMinimum().toString(), "1", "Bob The Banker");
		assertEquals(AccountType.SAVING, app.getCurrentAccount().getType());
	}

	@Test
	public void addAccountEmptyNameTest() {
		app.setCurrentUser(mockapp);
		sut.addAccount(sut.getStartingValueMinimum().toString(), "2", "");
		assertEquals(AccountType.CHECKING.toString(), app.getCurrentAccount().getName());
	}

	@Test
	public void addAccountQualifiedNameTest() {
		app.setCurrentUser(mockapp);
		sut.addAccount(sut.getStartingValueMinimum().toString(), "1", "Bob The Banker");
		assertEquals("Bob The Banker", app.getCurrentAccount().getName());
	}
}