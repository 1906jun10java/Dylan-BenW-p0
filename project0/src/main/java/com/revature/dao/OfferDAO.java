package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Offer;

public interface OfferDAO {
	public abstract List<Offer> readAllOffers() throws SQLException; //reads in all the data from the Offer table
	
	public abstract List<Offer> createNewOffer(int USERID, int CARID, double OFFERPRICE) 
		throws SQLException; //creates new offer and adds to database then reads in the new list of offers
}
