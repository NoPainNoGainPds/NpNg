package model;

import java.sql.Connection;
import java.sql.SQLException;
import db.Database;

public class ConnectionPool implements Pool {

	private Database db;
	public ConnectionPool(Database db) {
		this.db =db;
		initializePool();
	}
	
	public void newConnectionForPool() {
		Connection connection;
		try {
			connection = this.db.getConnection();
			connectionList.add(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
