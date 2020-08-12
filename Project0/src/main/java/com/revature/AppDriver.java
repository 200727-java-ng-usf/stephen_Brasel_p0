package com.revature;

import com.revature.dataStructures.Operator;
import com.revature.screens.Screen;
import com.revature.services.BankService;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class AppDriver {
	private static UserService userService;
	private static BankService bankService;
	private static Stack<Screen> screen;
	private static boolean running = true;

	public static UserService getUserService() {
		return userService;
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while(running){
			try{
				input = br.readLine();
			} catch(IOException ioe){
				ioe.printStackTrace();
			}
			switch(input){
				case "1":
					break;
				case "2":
					break;
				case "3":
				case "exit":
					running = false;
					break;
				case "hi":
					System.out.println("Well, hello!");
					break;
				case "Roll Tide!":
					System.out.println("Roll Tide!");
					break;
				default:
					break;
			}
			// TODO clear screen
//			clearConsole();

//			System.out.print("\033[H\033[2J");
//			System.out.flush();

//			try {
//				Runtime.getRuntime().exec("cls");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

//			try {
//				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public final static void clearConsole()	{
		try
		{
			final String os = System.getProperty("os.name");

			if (os.contains("Windows"))
			{
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//				Runtime.getRuntime().exec("cls");
			}
			else
			{
//				new ProcessBuilder("cmd", "/c", "clear").inheritIO().start().waitFor();
				Runtime.getRuntime().exec("clear");
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}


	private static void methodEnum() {
		String foo = calculate(Operator.ADDITION, 3.5, 2);
		String bar = String.valueOf(Operator.ADDITION.apply(3.5, 2));

		System.out.println(foo);
		System.out.println(bar);
		Set<Integer> ints = new TreeSet<>();
		ints.add(null);
		ints.add(4);
	}

	public static String calculate(Operator op, double x1, double x2){
		return String.valueOf(op.apply(x1, x2));
	}
}
