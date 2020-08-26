package com.revature.revabank.models;

import java.util.Arrays;

public enum AccountType {
	SAVING("Saving"),
	CHECKING("Checking");

	/**
	 * the String representation of the Account Type
	 */
	private String type;

	/**
	 * Creates this AccountType instance and gives it a String representation
	 * @param str
	 */
	private AccountType(String str){
		type = str;
	}

	/**
	 * Returns the <code>{@link AccountType}</code> associated with a given String value.
	 * @param name the name of the <code>{@link AccountType}</code>.
	 * @return the enumeration of the <code>{@link AccountType}</code>'s name.
	 */
	public static AccountType getByName(String name){
		return Arrays.stream(AccountType.values())
				.filter(role -> role.type.equals(name))
				.findFirst()
				.orElse(CHECKING);
	}

	/**
	 * Returns the .ordinal value for a given AccountType without having to worry about later sorting.
	 * @param accountType the <code>{@link AccountType}</code> to find the ordinal for.
	 * @return the int value of the index of the provided accountType.
	 */
	public static int getOrdinal(AccountType accountType){
		for (int i = 0; i < AccountType.values().length; i++) {
			if(AccountType.values()[i] == accountType) return i + 1;
		}
		return getOrdinal(AccountType.CHECKING);
	}

	@Override
	public String toString() {
		return type;
	}
}
