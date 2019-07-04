package com.revature.driver;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.beans.Car;
import com.revature.beans.User;
import com.revature.enums.UserType;
import com.revature.enums.ConditionType;
import com.revature.enums.OwnershipType;

public class Driver {
	
	public static void main(String[] args) 
	{
		Scanner kb = new Scanner(System.in);
		
		ArrayList<User> users = new ArrayList<User>();
		User user1 = new User("admin", "istrator", "admin", "passw0rd123", UserType.ADMIN);
		User user2 = new User("Dylan", "Waser", "waserd", "passw0rd123", UserType.CUSTOMER);
		User user3 = new User("Ben", "Wilson", "wilsonb", "passw0rd123", UserType.CUSTOMER);
		User user4 = new User("Emily", "Higgins", "higginse", "catNamedMerlin", UserType.CUSTOMER);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);

		ArrayList<Car> carsOnLot = new ArrayList<Car>();
		carsOnLot.add(new Car(1990, "Ford", "Fiesta", "Green", ConditionType.POOR));
		carsOnLot.add(new Car(1991, "Toyota", "Camry", "Black", ConditionType.EXCELLENT));
		carsOnLot.add(new Car(1992, "Chevrolet", "Corvette", "Blue", ConditionType.GOOD));
		carsOnLot.add(new Car(1993, "Porsche", "Cayman", "Red", ConditionType.FAIR));
		carsOnLot.add(new Car(1994, "Nissan", "Pathfinder", "Green", ConditionType.POOR));
		carsOnLot.add(new Car(1995, "Ford", "Windstar", "Purple", ConditionType.GOOD));
		carsOnLot.add(new Car(1996, "Chevrolet", "Tahoe", "White", ConditionType.GOOD));
		carsOnLot.add(new Car(1997, "Toyota", "Sequoia", "Tan", ConditionType.EXCELLENT));
		carsOnLot.add(new Car(1998, "Nissan", "Altima", "Orange", ConditionType.POOR));
		carsOnLot.add(new Car(1999, "Ford", "Mustang", "Yellow", ConditionType.FAIR));
		

		System.out.println("Welcome to Ben & Dylan's car lot.");
		User loggedIn = null;
		
		//ask for username & password
		System.out.println("Please enter your username.");
		String inputUsername = kb.next();
		
		System.out.println("Please enter your password");
		String inputPassword = kb.next();
		
		//check if username and password match anything in the system
		//if not get username and password again. print if they do not have an account contact dealership
		for(User user : users)
		{
			if(user.getUsername().equals(inputUsername))
			{
				if(user.getPassword().equals(inputPassword))
				{
					loggedIn = user;
				}
			}
		}
		if(loggedIn == null)
		{
			System.out.println("There is no username/password this that information.");
			System.exit(-1);
		}
		
		//once logged in, display menu from User class .menu() based on UserType
		loggedIn.menu();
		
		loggedIn.getOwnedCars().add(carsOnLot.get(0));
		carsOnLot.remove(0);
		//get a int from the client
		
		//go from there...
		System.out.println(loggedIn.toString());
		
		
		loggedIn.getOwnedCars().get(0).setOwnerShip(OwnershipType.OWNED);
		
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++" + "\n" +
							loggedIn.toString() + "\n" +
							"++++++++++++++++++++++++++++++++++++++++++++++");
		
		System.out.println("Printing cars on the lot :" + carsOnLot.toString());

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++" + "\n" +
							loggedIn.toString());
	}

}
