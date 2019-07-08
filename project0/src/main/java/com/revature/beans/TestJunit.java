package com.revature.beans;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.revature.daoimpl.CarDAOImpl;
import com.revature.daoimpl.UserDAOImpl;
import com.revature.driver.Driver;





public class TestJunit {
	static public CarDAOImpl cdi = new CarDAOImpl();
	static public UserDAOImpl udi = new UserDAOImpl();
	
	 @Test
		
	   public void testAdd() {
	      String str = "Junit is working fine";
	      assertEquals("Junit is working fine",str);
	 }     
	 @Test
	 
		public void databaseNull() {
		 assertNull(Driver.userLogin(udi.readAllUsers()));
	   }
	}

