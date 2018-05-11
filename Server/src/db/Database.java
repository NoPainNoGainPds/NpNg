package db;


import utils.Constants;

import java.sql.*;


/**
 * Represents the database
 */
public class Database {
	/**
	 * The information to connect the database
	 */
	private String url,user,password;
	/**
	 * A connection
	 */
	private Connection con = null;
	/*
	 * Chargement du driver de connexion a la base de donn�es.
	 * ceci ne s'execute qu'une seul fois
	 */
	static
	{
		try {

			Class.forName(Constants.DRIVER_NAME).newInstance();
			//DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
			int i = 1;
			System.out.println("*** Driver loaded.");
		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Constructor
	 */
	public Database()
	{

	}

	/**
	 * To connect the database
	 * @throws SQLException
	 */
	public void connect() throws SQLException
	{
		if(this.con==null)
			this.con = DriverManager.getConnection(this.url,this.user,this.password);
	}

	/**
	 * To get a connection
	 * @return a connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(this.url,this.user,this.password);
	}

	/**
	 * To get the url of the database
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * To set the url of the database
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * To get the user
	 * @return the user
	 */
	public String getuser() {
		return user;
	}

	/**
	 * Set the user
	 * @param user the new user
	 */
	public void setuser(String user) {
		this.user = user;
	}

	/**
	 * to get the password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * to set the password
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}