package com.revature.beans;

import java.util.ArrayList;

import com.revature.enums.UserType;

public class User {
	
	public User(String firstName, String lastName, String username, String password, UserType userType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private UserType userType = UserType.CUSTOMER;
	private ArrayList<Car> ownedCars = new ArrayList<Car>();
	
	
	
	
	
	//getters and setters
	public ArrayList<Car> getOwnedCars() {
		return ownedCars;
	}

	public void setOwnedCars(ArrayList<Car> ownedCars) {
		this.ownedCars = ownedCars;
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	//methods
	public void menu()
	{
		switch(userType)
		{
		case CUSTOMER:
				System.out.println("Please select an option from below. I.E. 1,2,3" + "\n" +
				"1. View cars for sale." + "\n" +
				"2. Make an offer on a car." + "\n" +
				"3. View my cars." + "\n" + 
				"4. View my remaining payments for a car.");
			break;
		case EMPLOYEE:
				System.out.println("Please select an option from below. I.E. 1,2,3" + "\n" + 
				"1. Add a car to the lot." + "/n" +
				"2. Accept or reject a pending offer." + "/n" +
				"3. Remove a car from the lot." + "/n" +
				"4. View all payments." );
			break;
		case ADMIN:
				System.out.println("Please select an option from below. I.E. 1,2,3" + "\n" + 
				"1. Update a car to owned following offer acceptance." + "/n" +
				"2. Calculate monthly payment.");
			break;
			default:
				System.out.println("Your usertype is invalid, please contact an admin.");
				System.exit(-1);
		}
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password + ", userType=" + userType + ", ownedCars=" + ownedCars + "]";
	}

	
}
