package com.revature.revabank.models;

import java.util.Date;

public class Transaction {
	private String id;
	private Date date;
	private String description;
	private double amount;
	private Account account;
//	private double balance;
	private AppUser owner;
	private AppUser transferee;
}
