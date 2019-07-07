package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Car;
import com.revature.dao.CarDAO;
import com.revature.beans.Car;
import com.revature.util.ConnFactory;

public class CarDAOImpl implements CarDAO 
{
	
	public static ConnFactory cf = ConnFactory.getInstance();
	
	public void createCar(String YEAR, String MAKE, String MODEL, String COLOR, int CONDITIONTYPEID, int OWNERSHIPTYPEID, int USERID) 
	{
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTCAR(?, ?, ?, ?, ?, ?, ?)";
		try {
			CallableStatement call = conn.prepareCall(sql);
			call.setString(1, YEAR);
			call.setString(2, MAKE);
			call.setString(3, MODEL);
			call.setString(4, COLOR);
			call.setInt(5, CONDITIONTYPEID);
			call.setInt(6, OWNERSHIPTYPEID);
			call.setInt(7, USERID);
			call.execute();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public ArrayList<Car> readAllCars()
	{
		ArrayList<Car> carList = new ArrayList<Car>();
		Connection conn = cf.getConnection();
		Statement stmt;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CAR");
			Car c = null;
			while(rs.next())
			{
				c = new Car(rs.getInt(1),		 //int carID
								rs.getString(2), //string year
								rs.getString(3), //String make
								rs.getString(4), //String model
								rs.getString(5), //String color
								rs.getInt(6),    //int conditionID
								rs.getInt(7),    //int OWNERSHIPTYPEID
								rs.getInt(8));	 //INT USERID
								
				carList.add(c);
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		return carList;
	}
	
	public void deleteCar(int carID)
	{
		Connection conn = cf.getConnection();
		String sql = "{ call DELETECAR(?)";
		try {
			CallableStatement call = conn.prepareCall(sql);
			call.setInt(1, carID);
			call.execute();
		} catch (SQLException e) {
			System.out.println("That car does not exist in the database try again.");
		}
	}
}
