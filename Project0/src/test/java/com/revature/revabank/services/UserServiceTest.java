package com.revature.revabank.services;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.repos.UserRepository;
import com.revature.revabank.util.AppState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.revature.revabank.AppDriver.app;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {
	UserService sut;
	UserRepository mopitory;
	AppUser mockapp;

	@Before
	public void setUp() throws Exception {
		mopitory = Mockito.mock(UserRepository.class);
		mockapp = Mockito.mock(AppUser.class);
		sut = new UserService(mopitory);
	}

	@After
	public void tearDown() throws Exception {
		sut = null;
	}

	@Test
	public void getAllUser() {
		assertNull(sut.getAllUser());
	}

	@Test
	public void getUsersByRole() {
		Role role = Role.PATRON;
		assertNull(sut.getUsersByRole(role));
	}

	@Test
	public void getUsersById() {
		assertNull(sut.getUsersById(1));
	}

	@Test
	public void getUserById() {
		assertNull(sut.getUserById(1));
	}

	@Test
	public void getUserByUsername() {
		assertNull(sut.getUserByUsername("aanderson"));
	}

	@Test(expected = InvalidRequestException.class)
	public void authenticateNullUserName() {
		sut.authenticate(null, "null");
	}

	@Test(expected = InvalidRequestException.class)
	public void authenticateNullPassword() {
		sut.authenticate("null", null);
	}

	@Test(expected = InvalidRequestException.class)
	public void authenticateNullUser() {
		sut.authenticate(null, null);
	}

	@Test(expected = InvalidRequestException.class)
	public void authenticateEmptyUserName() {
		sut.authenticate("", "password");
	}

	@Test(expected = InvalidRequestException.class)
	public void authenticateEmptyPassword() {
		sut.authenticate("username", "");
	}

	@Test(expected = InvalidRequestException.class)
	public void authenticateEmptyUser() {
		sut.authenticate("", "");
	}

	@Test
	public void authenticate() {
		when(mopitory.findUserByCredentials("aanderson", "password"))
				.thenReturn(java.util.Optional.of(
						new AppUser("Alice", "Anderson", "aanderson", "password", "aanderson@revature.net")));
		sut.authenticate("aanderson", "password");
		assertEquals(
				new AppUser("Alice", "Anderson", "aanderson", "password", "aanderson@revature.net") ,
				app.getCurrentUser());
	}

	@Test(expected = InvalidRequestException.class)
	public void registerEmptyFirstName() {
		when(mockapp.getFirstName()).thenReturn("");
		sut.register(mockapp);
	}

	@Test(expected = InvalidRequestException.class)
	public void registerEmptyLastName() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("");
		sut.register(mockapp);
	}

	@Test(expected = InvalidRequestException.class)
	public void registerEmptyUserName() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("");
		sut.register(mockapp);
	}

	@Test(expected = InvalidRequestException.class)
	public void registerEmptyPassword() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn("");
		sut.register(mockapp);
	}

	@Test(expected = InvalidRequestException.class)
	public void registerEmptyEmail() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn("password");
		when(mockapp.getEmail()).thenReturn("");
		sut.register(mockapp);
	}

	@Test(expected = InvalidRequestException.class)
	public void registerNullFirstName() {
		when(mockapp.getFirstName()).thenReturn(null);
		sut.register(mockapp);
	}

	@Test(expected = InvalidRequestException.class)
	public void registerNullLastName() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn(null);
		sut.register(mockapp);
	}

	@Test(expected = InvalidRequestException.class)
	public void registerNullUserName() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn(null);
		sut.register(mockapp);
	}

	@Test(expected = InvalidRequestException.class)
	public void registerNullPassword() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn(null);
		sut.register(mockapp);
	}

	@Test(expected = InvalidRequestException.class)
	public void registerNullEmail() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn("password");
		when(mockapp.getEmail()).thenReturn(null);
		sut.register(mockapp);
	}

	@Test(expected = AuthenticationException.class)
	public void registerExistingUser() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn("password");
		when(mockapp.getEmail()).thenReturn("aanderson@revature.net");
		when(mopitory.findUserByUsername("aanderson"))
				.thenReturn(java.util.Optional.of(
						new AppUser("Alice", "Anderson", "aanderson", "password", "aanderson@revature.net")));
		sut.register(mockapp);
	}

	@Test
	public void register() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn("password");
		when(mockapp.getEmail()).thenReturn("aanderson@revature.net");
		when(mopitory.findUserByUsername("aanderson"))
				.thenReturn(java.util.Optional.empty());
		sut.register(mockapp);
		assertEquals(
				mockapp,
				app.getCurrentUser());
	}

	@Test
	public void updateUser() {
		assertFalse(sut.updateUser(mockapp));
	}

	@Test
	public void deleteUserById() {
		when(mockapp.getId()).thenReturn(-1);
		assertFalse(sut.deleteUserById(mockapp.getId()));
	}

	@Test
	public void validateUserFields() {
		assertFalse(sut.validateUserFields(mockapp));
	}

	@Test
	public void isUserValid() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn("password");
		when(mockapp.getEmail()).thenReturn("aanderson@revature.net");
		assertTrue(sut.isUserValid(mockapp));
	}

	@Test
	public void isUserValidEmptyFirstName() {
		when(mockapp.getFirstName()).thenReturn("");
		assertFalse(sut.isUserValid(mockapp));
	}

	@Test
	public void isUserValidEmptyLastName() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("");
		assertFalse(sut.isUserValid(mockapp));
	}

	@Test
	public void isUserValidEmptyUserName() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("");
		assertFalse(sut.isUserValid(mockapp));
	}

	@Test
	public void isUserValidEmptyPassword() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn("");
		assertFalse(sut.isUserValid(mockapp));
	}

	@Test
	public void isUserValidEmptyEmail() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn("password");
		when(mockapp.getEmail()).thenReturn("");
		assertFalse(sut.isUserValid(mockapp));
	}

	@Test
	public void isUserValidNullFirstName() {
		when(mockapp.getFirstName()).thenReturn(null);
		assertFalse(sut.isUserValid(mockapp));
	}

	@Test
	public void isUserValidNullLastName() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn(null);
		assertFalse(sut.isUserValid(mockapp));
	}

	@Test
	public void isUserValidNullUserName() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn(null);
		assertFalse(sut.isUserValid(mockapp));
	}

	@Test
	public void isUserValidNullPassword() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn(null);
		assertFalse(sut.isUserValid(mockapp));
	}

	@Test
	public void isUserValidNullEmail() {
		when(mockapp.getFirstName()).thenReturn("Alice");
		when(mockapp.getLastName()).thenReturn("Anderson");
		when(mockapp.getUserName()).thenReturn("aanderson");
		when(mockapp.getPassword()).thenReturn("password");
		when(mockapp.getEmail()).thenReturn(null);
		assertFalse(sut.isUserValid(mockapp));
	}
}