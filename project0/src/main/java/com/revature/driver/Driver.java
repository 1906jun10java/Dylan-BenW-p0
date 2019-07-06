package com.revature.driver;

import java.awt.Menu;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.beans.Car;
import com.revature.beans.User;
import com.revature.daoimpl.CarDAOImpl;
import com.revature.daoimpl.UserDAOImpl;
import com.revature.enums.UserType;
import com.revature.enums.ConditionType;
import com.revature.enums.OwnershipType;

public class Driver {
	
	public static void main(String[] args) 
	{
		Scanner kb = new Scanner(System.in);
		UserDAOImpl udi = new UserDAOImpl();
		CarDAOImpl cdi = new CarDAOImpl();
		
		ArrayList<User> users = udi.readAllUsers();
		ArrayList<Car> carsOnLot = cdi.readAllCars();
		
		/*
		 * THIS IS TO INSERT A NEW CAR INTO DATABASE FROM AND EMPLOYEE ACCOUNT
		 */
		cdi.createCar("2019", "Type C", "Telsa", "Black", 1, 1, 1);
	
	/* THIS IS TO INSERT A NEW USER INTO DATABASE FROM AN EMPLOYEE ACCOUNT	
		User todd = new User(users.size()+1, "Todd", "Stevens", "hottytoddy", "passw0rd123", 3);
	
		try {
			udi.createUser("Todd", "Stevens", "hottytoddy", "passw0rd123", 3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
		
/*
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
							loggedIn.toString());  */
	}

}
