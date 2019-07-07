package com.revature.dao;

import java.sql.SQLException;
import java.util.List;
import com.revature.beans.User;

public interface UserDAO {
	//CRUD OPS
		public abstract List<User> createUser (String firstName, String lastName, String username, String passw0rd, int titleNum) throws SQLException;
		
		public abstract List<User> readAllUsers() throws SQLException;

}
