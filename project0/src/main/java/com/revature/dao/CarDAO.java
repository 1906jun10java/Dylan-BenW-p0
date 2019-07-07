package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Car;
import com.revature.beans.User;

public interface CarDAO {
	//CRUD OPS
			public abstract void createCar (String YEAR, String MAKE, String MODEL, String COLOR, int CONDITIONTYPEID, int OWNERSHIPTYPEID, int USERID) throws SQLException;
			
			public abstract List<Car> readAllCars() throws SQLException;
			
			public abstract void deleteCar(int carID) throws SQLException;
			
}
