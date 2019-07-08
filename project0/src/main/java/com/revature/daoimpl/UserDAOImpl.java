package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Car;
import com.revature.beans.User;
import com.revature.dao.UserDAO;
import com.revature.util.ConnFactory;

public class UserDAOImpl implements UserDAO
{
	public static ConnFactory cf = ConnFactory.getInstance();
	
	public ArrayList<User> createUser(String FIRSTNAME, String LASTNAME, String USERNAME, String PASSW0RD, int USERTYPEID) throws SQLException
	{
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTUSER(?, ?, ?, ?, ?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, FIRSTNAME);
		call.setString(2, LASTNAME);
		call.setString(3, USERNAME);
		call.setString(4, PASSW0RD);
		call.setInt(5, USERTYPEID);
		call.execute();
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM US3R");
			User u = null;
			while(rs.next())
			{
				u = new User(rs.getInt(1), //int userID
								rs.getString(2), //String firstName
								rs.getString(3), //String lastName
								rs.getString(4), //String username
								rs.getString(5), //String password
								rs.getInt(6));	 //int titleNum
								
				userList.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	public ArrayList<User> readAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = cf.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM US3R");
			User u = null;
			while(rs.next())
			{
				u = new User(rs.getInt(1), //int userID
								rs.getString(2), //String firstName
								rs.getString(3), //String lastName
								rs.getString(4), //String username
								rs.getString(5), //String password
								rs.getInt(6));	 //int titleNum
								
				userList.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	
	public User readUser(int USERID)
	{
		User u = null;
		Connection conn = cf.getConnection();
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM US3R WHERE USERID = ?");
			while(rs.next())
			{
				u = new User(rs.getInt(1),		 //int userID
						rs.getString(2), //string firstName
						rs.getString(3), //String lastName
						rs.getString(4), //String username
						rs.getString(5), //String password
						rs.getInt(6));   //int titleNum
			}
		}
		catch(SQLException ex)
		{
			System.out.println("Could not find that user.");
			System.exit(-1);
		}
		return u;
	}
}
