package model;

import java.sql.Connection;
import java.sql.SQLException;
import db.Database;

/**
 * Represents a connection pool
 */
public class ConnectionPool implements Pool {

	/**
	 * The database
	 */
	private Database db;

	/**
	 * Constructor
	 * @param db the database
	 */
	public ConnectionPool(Database db) {
		this.db =db;
		initializePool();
	}

	/**
	 * create a new connection for the pool
	 */
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

	/**
	 * Initialize the pool to MAX_POOL_SIZE connections
	 */
	public void initializePool() {
		for(int i = 0 ; i < MAX_POOL_SIZE; i++) {
			newConnectionForPool();
		}
	}

	/**
	 * Get a connection from the pool
	 * @return a connection
	 */
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

	/**
	 * Return a connection to the pool
	 * @param connection the connection to return to the pool
	 */
	public synchronized void returnConnectionToPool(Connection connection) {
		connectionList.add(connection);
	}


}
