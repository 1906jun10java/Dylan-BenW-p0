package com.revature.beans;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.daoimpl.CarDAOImpl;
import com.revature.daoimpl.UserDAOImpl;
import com.revature.enums.OwnershipType;
import com.revature.enums.UserType;

public class User {
	static public UserDAOImpl udi = new UserDAOImpl();
	static public CarDAOImpl cdi = new CarDAOImpl();
	
	public User(int userID, String firstName, String lastName, String username, String password, int titleNum) {
		super();		
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		switch(titleNum)
		{
		case 1:
			this.title = UserType.ADMIN;
			break;
		case 2:
			this.title = UserType.EMPLOYEE;
			break;
		case 3:
			this.title = UserType.CUSTOMER;
			break;
		}
	}

	private int userID;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private UserType title = UserType.CUSTOMER;
	private ArrayList<Car> ownedCars = new ArrayList<Car>();
	
	//getters and setters
	public ArrayList<Car> getOwnedCars() {
		return ownedCars;
	}

	public void setOwnedCars(ArrayList<Car> ownedCars) {
		this.ownedCars = ownedCars;
	}
	
	public int getUserID()
	{
		return userID;
	}
	
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTitle() {
		switch(title)
		{
		case ADMIN:
			return 1;
		case EMPLOYEE:
			return 2;
		case CUSTOMER:
			return 3;
		default:
			return 3;
		}
	}

	public void setTitle(int titleNum) {
		switch(titleNum)
		{
		case 1:
			this.title = UserType.ADMIN;
			break;
		case 2:
			this.title = UserType.EMPLOYEE;
			break;
		case 3:
			this.title = UserType.CUSTOMER;
			break;
		}
	}
	
	//methods
	public void menu(Scanner kb) throws SQLException
	{
		int menuInput = 0;
		while(menuInput != 5)
		{
			switch(title)
			{
			case CUSTOMER:
				while(menuInput > 5 || menuInput < 1)
				{
					System.out.println("Please select an option from below. I.E. 1,2,3" + "\n" +
					"1. View cars that are on the lot." + "\n" +
					"2. Make an offer on a car." + "\n" +
					"3. View my cars." + "\n" + 
					"4. View my remaining payments for a car." + "\n" +
					"5. Exit");	
					menuInput = kb.nextInt();
					if(menuInput > 5 || menuInput < 1)
					{
						System.out.println("Invalid option, please try again.");
						continue;
					}
					switch(menuInput)
					{
					case 1:
						ArrayList<Car> carsOnLot = cdi.readAllCars();
						System.out.println("Printing all cars that are on the lot.");
						for(Car c : carsOnLot)
						{
							System.out.println(c.toString());
						}
						break;
					case 3:
						System.out.println(this.ownedCars());
						break;
					case 5:
						System.out.println("Goodbye");
						System.exit(-1);
					}
					menuInput = 0;
				}
				break;
			case EMPLOYEE:
				while(menuInput > 5 || menuInput < 1)
				{
					System.out.println("Please select an option from below. I.E. 1,2,3" + "\n" + 
					"1. Add a car to the lot." + "\n" +
					"2. Accept or reject a pending offer." + "\n" +
					"3. Remove a car from the lot." + "\n" +
					"4. View all payments." + "\n" +
					"5. Exit");
					menuInput = kb.nextInt();
					if(menuInput > 5 || menuInput < 1)
					{
						System.out.println("Invalid option, please try again.");
						continue;
					}
					switch(menuInput)
					{
					case 1:
						System.out.println("What is the year of the car?");
						String newYear = kb.next();
						
						System.out.println("What is the make of the car?");
						String newMake = kb.next();
						
						System.out.println("What is the model of the car?");
						String newModel = kb.next();
						
						System.out.println("What color is the car?");
						String newColor = kb.next();
						
						int newConditionTypeID = 0;
						while(newConditionTypeID < 1 || newConditionTypeID > 4)
						{
							try
							{
								System.out.println("What is the condition of the car? Enter the number 1,2,3, etc." + "\n" +
													"1. Excellent" + "\n" +
													"2. Good" + "\n" + 
													"3. Fair" + "\n" +
													"4. Poor");
								newConditionTypeID = kb.nextInt();
							}
							catch(InputMismatchException ex)
							{
								System.out.println("That is not a number");
								newConditionTypeID = 0;
								continue;
							}
							if(newConditionTypeID < 1 || newConditionTypeID > 4)
							{
								System.out.println("Invalid option please enter correct number");
							}
						}
						int newOwnershipTypeID = 1;
						int newUserID = 1;
						cdi.createCar(newYear, newMake, newModel, newColor, newConditionTypeID, newOwnershipTypeID, newUserID);
						System.out.println("The car has been created and saved.");
						break;
					case 3:
						int optionCarID = 0;
						while(optionCarID == 0)
						{
							try {
								System.out.println("Please input the carID of which you would like to delete.");
								for(Car c : cdi.readAllCars())
								{
									if(c.getOwnershipType() == OwnershipType.FORSALE)
									{
										System.out.println(c.toString());
									}
								}
								optionCarID = kb.nextInt();
								cdi.deleteCar(optionCarID);
								System.out.println("Car has been succesfully deleted.");
							}
							catch(InputMismatchException inputException)
							{
								System.out.println("That is not a valid carID please try again");
								optionCarID = 0;
								continue;
							}
							catch(Exception e)
							{
								System.out.println("Something went wrong.");
								optionCarID = 0;
								continue;
							}
						}
						break;
					case 5:
						System.out.println("Goodbye");
						System.exit(-1);
					}
				}
				menuInput = 0;
				break;
			case ADMIN:
					System.out.println("Please select an option from below. I.E. 1,2,3" + "\n" + 
					"1. Update a car to owned following offer acceptance." + "/n" +
					"2. Calculate monthly payment." + "\n" +
					"5. Exit");
				break;
				default:
					System.out.println("Your usertype is invalid, please contact an admin.");
					System.exit(-1);
			}
		}
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", title=" + title + ", ownedCars=" + ownedCars + "]";
	}
	
	public String ownedCars()
	{
		String str = "";
		for(Car car : this.ownedCars)
		{
			str += "Car [Year = " + car.getYear() + ", Make = " + car.getMake() + ", Model = " + car.getModel() + ", Color = " + car.getColor()
				+ ", Condition = " + car.getCondition() + "\n";
		}
		
		return str;
	}
}
