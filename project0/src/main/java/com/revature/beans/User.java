package com.revature.beans;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.daoimpl.CarDAOImpl;
import com.revature.daoimpl.OfferDAOImpl;
import com.revature.daoimpl.UserDAOImpl;
import com.revature.enums.OwnershipType;
import com.revature.enums.UserType;

public class User {
	static public UserDAOImpl udi = new UserDAOImpl();
	static public CarDAOImpl cdi = new CarDAOImpl();
	static public OfferDAOImpl odi = new OfferDAOImpl();
	
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
					case 1: //prints all cars on the lot to the screen
						System.out.println("Printing all cars that are on the lot.");
						for(Car c : cdi.readAllCars()) //loops through the car list from the database
						{							   //and prints each cars toString
							System.out.println(c.toString());
						}
						break;
						
					case 2: //make an offer on a car
						ArrayList<Car> cars = cdi.readAllCars(); //read in the CAR table
						System.out.println("These are the cars for sale.");
						int counter = 1;
						for(Car c : cdi.readAllCars()) //prints only cars that are for sale
						{
							if(c.getOwnershipType() == OwnershipType.FORSALE)
							{
								System.out.println(counter + ". " + c.toString());
							}
							counter++;
						}
						int carSelection = 0;
						while(carSelection < 1 || carSelection > cars.size())
						{
							System.out.println("Please enter the number for the car you would like to make an offer on.");
							carSelection = kb.nextInt() - 1;
							if(carSelection < 1 || carSelection > cars.size())
							{
								System.out.println("That is not a valid option, please try again.");
								carSelection = 0;
								continue;
							}
						}
						
						System.out.println("What is your offer on the car?");
						double offerPrice = kb.nextDouble();
						odi.createNewOffer(this.userID, cars.get(carSelection).getCarID(), offerPrice);
						//adds a new offer to the database
						
						System.out.println("Thank you, you will be notified when your offer is accepted/declined");						
						break;
						
					case 3:
						System.out.println("You own these cars." + "\n" +
											this.ownedCars());
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
						System.out.println("What is the price of the car? E.g. 12000.00");
						double newPrice = kb.nextDouble();
						cdi.createCar(newYear, newMake, newModel, newColor, newConditionTypeID, newOwnershipTypeID, newUserID, newPrice);
						System.out.println("The car has been created and saved.");
						break;
					case 2:
						System.out.println("Printing all pending offers.");
						ArrayList<Offer> offerList = odi.readAllOffers();
						ArrayList<Car> cars = cdi.readAllCars();
						for(Offer o : offerList)
						{
							System.out.println(o.toString());
						}
						System.out.println("Enter the offerID you would like to accept/decline.");
						int inputOption = 0;
						while(inputOption == 0)
						{
							try
							{
								inputOption = kb.nextInt();
								if(inputOption < 1 || inputOption > offerList.size())
								{
									throw new InputMismatchException();
								}
								
							}
							catch(InputMismatchException ex)
							{
								System.out.println("Invalid option, try again.");
								inputOption = 0;
								continue;
							}
						}
						Offer o = offerList.get(inputOption-1);
						System.out.println("Would you like to accept or decline this offer?" + "\n" +
											"1. Accept" + "\n" +
											"2. Decline");
						inputOption = 0;
						while(inputOption == 0)
						{
							try
							{
								inputOption = kb.nextInt();
								if(inputOption < 1 || inputOption > 2)
								{
									throw new InputMismatchException();
								}
							}
							catch(InputMismatchException ex)
							{
								System.out.println("Invalid option, try again.");
								inputOption = 0;
								continue;
							}
						}
						if(inputOption == 1)
						{
							//set the UserId and OwnershipType of the carID in the offer for the car
							User u = udi.readUser(o.getUserID());
							Car c = cdi.readCar(o.getCarID());
							c.setOwnershipType(OwnershipType.OWNED);
							cdi.updateCar(c.getCarID(), u.getUserID());
						}
						else if(inputOption == 2) //deletes an offer from the database on the offerID
						{
							odi.deleteOffer(o.getOfferID());
						}
						
						
						
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
					"1. Calculate monthly payment." + "\n" +
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
			str += car.getYear() + " " + car.getMake() + " " + car.getModel() + ", " + car.getColor()
			+ " " + car.getCondition() + " " + "condition" + "\n";
		}
		
		return str;
	}
}
