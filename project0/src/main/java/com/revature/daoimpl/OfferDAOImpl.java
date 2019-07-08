package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Offer;
import com.revature.beans.User;
import com.revature.dao.OfferDAO;
import com.revature.util.ConnFactory;

public class OfferDAOImpl implements OfferDAO
{
	public static ConnFactory cf = ConnFactory.getInstance();
	
	public  ArrayList<Offer> createNewOffer(int USERID, int CARID, double OFFERPRICE)
	{
		ArrayList<Offer> offerList = new ArrayList<Offer>();
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTOFFER(?, ?, ?)";
		try
		{
			CallableStatement call = conn.prepareCall(sql);
			call.setInt(1, USERID);
			call.setInt(2, CARID);
			call.setDouble(3, OFFERPRICE);
			call.execute();
			
			Statement stmt;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM OFFER");
			Offer o = null;
			while(rs.next())
			{
				o = new Offer(rs.getInt(1), //int offerID
								rs.getInt(2), //int userID
								rs.getInt(3), //int carID
								rs.getDouble(4)); //double offerAmount
				offerList.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return offerList;
	}


	public ArrayList<Offer> readAllOffers() throws SQLException 
	{
		ArrayList<Offer> offerList = new ArrayList<Offer>();
		Connection conn = cf.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM OFFER");
			Offer o = null;
			while(rs.next())
			{
				o = new Offer(rs.getInt(1), //int offerID
								rs.getInt(2), //int userID
								rs.getInt(3), //int carID
								rs.getDouble(4)); //double offerAmount
				offerList.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return offerList;
	}
	
	public void deleteOffer(int offerID)
	{
		Connection conn = cf.getConnection();
		String sql = "{ call DELETEOFFER(?)";
		try {
			CallableStatement call = conn.prepareCall(sql);
			call.setInt(1, offerID);
			call.execute();
		} catch (SQLException e) {
			System.out.println("That offer does not exist in the database try again.");
		}
	}
}