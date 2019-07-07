package com.revature.driver;

import java.awt.Menu;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.Car;
import com.revature.beans.User;
import com.revature.daoimpl.CarDAOImpl;
import com.revature.daoimpl.UserDAOImpl;
import com.revature.enums.UserType;
import com.revature.enums.ConditionType;
import com.revature.enums.OwnershipType;

public class Driver {
	static public Scanner kb = new Scanner(System.in);
	static public UserDAOImpl udi = new UserDAOImpl();
	static public CarDAOImpl cdi = new CarDAOImpl();
	private static final Logger log4j = LogManager.getLogger(Driver.class);
	 
	
	public static void main(String[] args) 
	{		
		ArrayList<User> users = udi.readAllUsers();
		ArrayList<Car> carsOnLot = cdi.readAllCars();
		
		int menuInput = 0;
		User loggedin = null;
		
		/*
		 * THIS IS TO INSERT A NEW CAR INTO DATABASE FROM AND EMPLOYEE ACCOUNT
		 */
		//cdi.createCar("2019", "Type C", "Telsa", "Black", 1, 1, 1);
		
		//initial menu options
		while(menuInput > 3 || menuInput < 1)
		{
			System.out.println(introMenu());
			try {
				menuInput = kb.nextInt();
			}
			catch(InputMismatchException inputException)
			{
				log4j.error("The client entered something other than a number");
				menuInput = 0;
				continue;
			}
			catch(Exception e)
			{
				menuInput = 0;
				continue;
			}
			
			if(3 < menuInput || menuInput < 1)
			{
				System.out.println("Invalid option, please try again.");
				continue;
			}
			switch(menuInput)
			{
			case 1:
				loggedin = userLogin(users);
				break;
			case 2:
				loggedin = createNewUser();
				break;
			case 3:
				System.out.println("Goodbye");
				System.exit(-1);
			}
		}	
		
		//based on the loggedin users type it prints a certain menu from the user class
		System.out.println("Welcome " + loggedin.getFirstName() + " " + loggedin.getLastName() + " what would you like to do?");
		assignCars(loggedin, carsOnLot);
		loggedin.menu(kb);
		kb.close();
	}
	
	//menu methods
	public static String introMenu()
	{
		return "Welcome to Ben & Dylan's car lot." + "\n" +
					"What would you like to do?" + "\n" +
					"1. Log-in" + "\n" +
					"2. Create new account" + "\n" +
					"3. Exit";
	}
	
	public static User userLogin(ArrayList<User> users) //returns a user based on username and password from client
	{
		User loggedIn = null;
		
		//ask for username & password
		System.out.println("Please enter your username.");
		String inputUsername = kb.next();
		
		System.out.println("Please enter your password");
		String inputPassword = kb.next();
		
		//check if username and password match anything in the system
		//if not get username and password again. print if they do not have an account contact dealership
		if(users != null)
		{	
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
		}
		if(loggedIn == null)
		{
			System.out.println("There is no username/password this that information.");
			System.exit(-1);
		}
		return loggedIn;
	}
	
	public static User createNewUser() //creates a new user and send to database and returns that new user
	{		
		System.out.println("What is your first name?");
		String newFirstName = kb.next();
		
		System.out.println("What is your last name?");
		String newLastName = kb.next();
		
		System.out.println("What woud you like your username to be?");
		String newUsername = kb.next();
		
		System.out.println("What would you like your password to be?");
		String newPassword = kb.next();
		
		System.out.println("By default your new account will be setup for a \"Customer\" account.");
	
		ArrayList<User> users;
		try {
			users = udi.createUser(newFirstName, newLastName, newUsername, newPassword, 3);
			for(User user : users)
			{
				if(user.getUsername().equals(newUsername))
				{
					if(user.getPassword().equals(newPassword))
					{
						 return user;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void assignCars(User loggedin, ArrayList<Car> cars)
	{
		if(loggedin != null)
		{
			for(int i = 0; i <= cars.size()-1; i++)
			{
				if(cars.get(i).getUserID() == loggedin.getUserID())
				{
					loggedin.getOwnedCars().add(cars.get(i));
				}
			}
		}
		else {
			System.out.println("There is not a valid person loggedin, goodbye");
		}
	}

}
