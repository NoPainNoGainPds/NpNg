package utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool implements Pool {

	public ConnectionPool() {
	    try {
	        ObjectMapper om = new ObjectMapper();
	        File file = new File("DBProperties.txt");
	        //Database db = om.readValue(file,Database.class);
			Constants.DB = om.readValue(file,Database.class);
        } catch(Exception e) {
	    	e.printStackTrace();
		}
		initializePool();
	}
	
	public void newConnectionForPool() {
		Connection connection;
		connection = Constants.DB.getConnection();
		connectionList.add(connection);
	}
	
	public void initializePool() {
		for(int i = 0 ; i < MAX_POOL_SIZE; i++) {
			newConnectionForPool();
		}
	}
	
	public synchronized Connection getConnectionFromPool() {
		Connection connection = null;
		if(connectionList.size() == 0) {
			newConnectionForPool();
			connection = connectionList.get(0);
			connectionList.remove(0);
		}
		else {
			if(connectionList.size() > 0) {
				connection = connectionList.get(0);
				connectionList.remove(0);
			}
		}
		return connection;
	}
	
	public synchronized void returnConnectionToPool(Connection connection) {
		connectionList.add(connection);
	}


}
