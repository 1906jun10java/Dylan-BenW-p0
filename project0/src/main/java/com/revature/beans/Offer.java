package com.revature.beans;

public class Offer {
	private int offerID;
	private int userID;
	private int carID;
	private double offerAmount;
	
	public Offer(int offerID, int userID, int carID, double offerAmount) {
		super();
		this.offerID = offerID;
		this.userID = userID;
		this.carID = carID;
		this.offerAmount = offerAmount;
	}

	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public double getOfferAmount() {
		return offerAmount;
	}

	public void setOfferAmount(double offerAmount) {
		this.offerAmount = offerAmount;
	}

	@Override
	public String toString() {
		return "Offer [offerID=" + offerID + ", userID=" + userID + ", carID=" + carID + ", offerAmount=" + offerAmount
				+ "]";
	}	
}
