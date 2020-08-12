package com.revature.models;

public enum Role {
	ADMIN("Admin"){},
	MANAGER("Manager"){},
	CLERK("Clerk"){},
	PATRON("User"){},
	LOCKED("Locked"){}
	;

	private String type;

	Role(String type){
		this.type = type;
	}

}
