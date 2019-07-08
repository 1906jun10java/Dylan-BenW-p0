package com.revature.beans;

import com.revature.enums.ConditionType;
import com.revature.enums.OwnershipType;
import org.apache.log4j.Logger;

public class Car {
	
	public Car(int carID, String year, String make, String model, String color, int conditionNum, int ownershipTypeNum, int userID, double price) {
		super();
		this.carID = carID;
		this.year = year;
		this.make = make;
		this.model = model;
		this.color = color;
		switch(conditionNum)
		{
		case 1:
			this.condition = ConditionType.EXCELLENT;
			break;
		case 2: 
			this.condition = ConditionType.GOOD; 
			break;
		case 3:
			this.condition = ConditionType.FAIR; 
			break;
		case 4: 
			this.condition = ConditionType.POOR;
			break;
		}
		switch(ownershipTypeNum)
		{
		case 1: 
			this.ownerShip = OwnershipType.FORSALE;
			break;
		case 2: 
			this.ownerShip = OwnershipType.OWNED; 
			break;
		}
		this.userID = userID;
		this.price = price;
	}

	private int carID;
	private String year;
	private String make;
	private String model;
	private String color;
	private ConditionType condition = ConditionType.GOOD;
	private OwnershipType ownerShip = OwnershipType.FORSALE;
	private int userID = 1;
	private double price;
	
	//getters and setters
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public int getUserID()
	{
		return userID;
	}
	
	public void setUserID(int userID)
	{
		if(userID == 1)
		{
			this.ownerShip = OwnershipType.FORSALE;
		}
		else
		{
			this.ownerShip = OwnershipType.OWNED;
		}
	}
	
	public OwnershipType getOwnershipType() {
		return ownerShip;
	}

	public void setOwnershipType(OwnershipType ownerShip) {
		this.ownerShip = ownerShip;
	}
	
	public int getCarID()
	{
		return carID;
	}
	
	public void setCarID(int carID)
	{
		this.carID = carID;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ConditionType getCondition() {
		return condition;
	}

	public void setCondition(int conditionID) {
		switch(conditionID)
		{
		case 1:
			this.condition = ConditionType.EXCELLENT;
			break;
		case 2: 
			this.condition = ConditionType.GOOD;
			break;
		case 3: 
			this.condition = ConditionType.FAIR;
			break;
		case 4:
			this.condition = ConditionType.POOR; 
			break;
		}
	}
	
	public int getOwnership(){
		switch(ownerShip)
		{
		case FORSALE: 
			return 1;
		case OWNED: 
			return 2;
		default:
			return 1;
		}
	}

	public void setOwnership(int ownershipTypeNum){
		switch(ownershipTypeNum)
		{
		case 1:
			this.ownerShip = OwnershipType.FORSALE;
			break;
		case 2: 
			this.ownerShip = OwnershipType.OWNED;
			break;
		}
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carID;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((ownerShip == null) ? 0 : ownerShip.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + userID;
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (carID != other.carID)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (condition != other.condition)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (ownerShip != other.ownerShip)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (userID != other.userID)
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return year + " " + make + " " + model + " ," + color
				+ " " + condition + " condition, Price: $" + price + "]";
	}

			
}
