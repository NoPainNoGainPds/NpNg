package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class which contains the information required to connect the database
 * @author nassi
 *
 */
public class Database {
	/**
	 * The driver's name (mysql is used here)
	 */
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	/**
	 * The url of the database
	 */
	private static final String URL = "jdbc:mysql://192.168.1.3/PDSTest";
	/**
	 * The login to access the database
	 */
	private static final String USER = "root";
	/**
	 * The password to access the database
	 */
	private static final String PASSWORD = "npng";
	
	
	static {
		try {
			Class.forName(DRIVER_NAME).newInstance();
			System.out.println("*** Driver loaded ***");
		}
		catch (ClassNotFoundException e) {
			System.err.println("*** ERROR: Driver " + DRIVER_NAME + "not found");
		}
		catch (InstantiationException e) {
			System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
			System.err.println(e.getMessage());
		}
		catch (IllegalAccessException e) {
			System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * This methods get a connection from DriverManager and return it
	 * @return
	 * 			The connection
	 * @throws SQLException
	 * 			if there are errors by accessing the database
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
