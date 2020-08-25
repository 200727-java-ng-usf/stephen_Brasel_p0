package com.revature.revabank.models;

import java.util.Arrays;

public enum AccountType {
	SAVING("Saving"),
	CHECKING("Checking");

	private String type;

	private AccountType(String str){
		type = str;
	}

	public static AccountType getByName(String name){
		return Arrays.stream(AccountType.values())
				.filter(role -> role.type.equals(name))
				.findFirst()
				.orElse(CHECKING);
	}

	public static int getOrdinal(AccountType accountType){
		for (int i = 0; i < Role.values().length; i++) {
			if(AccountType.values()[i] == accountType) return i+1;
		}
		return getOrdinal(AccountType.CHECKING);
	}

	@Override
	public String toString() {
		return type;
	}
}
