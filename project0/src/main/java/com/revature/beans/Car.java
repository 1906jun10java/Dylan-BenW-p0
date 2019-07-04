package com.revature.beans;

import com.revature.enums.ConditionType;
import com.revature.enums.OwnershipType;

public class Car {

	//constructors
	public Car() {
		// TODO Auto-generated constructor stub
	}
	
	public Car(int year, String make, String model, String color, ConditionType condition) {
		super();
		this.year = year;
		this.make = make;
		this.model = model;
		this.color = color;
		this.condition = condition;
	}


	private int year;
	private String make;
	private String model;
	private String color;
	private ConditionType condition = ConditionType.GOOD;
	private OwnershipType ownerShip = OwnershipType.FORSALE;
	
	//getters and setters
	public OwnershipType getOwnerShip() {
		return ownerShip;
	}

	public void setOwnerShip(OwnershipType ownerShip) {
		this.ownerShip = ownerShip;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
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

	public void setCondition(ConditionType condition) {
		this.condition = condition;
	}

	
	//methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + year;
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
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [year=" + year + ", make=" + make + ", model=" + model + ", color=" + color + ", condition="
				+ condition + ", ownerShip=" + ownerShip + "]";
	}

	
}
