package utils;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Interface which can be implemented by a connection pool
 * @author npng
 *
 */
public interface Pool {
	/**
	 * The ArrayList connectionList contains all the connections
	 */
	public ArrayList<Connection> connectionList = new ArrayList<Connection>();
	/**
	 * MAX_POOL_SIZE is the max amount of connection in the ArrayList
	 */
	public final int MAX_POOL_SIZE = 20;
	/**
	 * Create a connection and add it to the pool
	 */
	public void newConnectionForPool();
	/**
	 * Get a connection from the pool, and then remove it from the pool to prevent a thread from picking this already use one
	 * @return The connection
	 */
	public Connection getConnectionFromPool();
	/**
	 * Return the connection to the pool
	 * @param connection the connection to return to the pool
	 */
	public void returnConnectionToPool(Connection connection);
	/**
	 * Initialize the pool by adding MAX_POOL_SIZE connections to it
	 */
	public void initializePool();
}
