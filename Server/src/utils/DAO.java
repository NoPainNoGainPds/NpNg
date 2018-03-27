package utils;

import controller.Client;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
    protected Connection connection;
    public DAO(Connection connection)
    {
        this.connection = connection;
    }
    public abstract boolean create(T obj);


    /**

     * Method to delete

     * @param obj object to delete

     * @return boolean

     */

    public abstract boolean delete(T obj);


    /**

     * Update method

     * @param obj object to update

     * @return boolean

     */

    public abstract boolean update(T obj);


    /**

     * Search method

     * @param id id of the object to search

     * @return T

     */

    public abstract T find(int id);

    /**
     * Search method in all the tables
     * thanks to a foreign key
     *
     * @param id
     * @return ArrayList<T>
     */
    public abstract ArrayList<T> findFromReference(int id);
    public abstract ArrayList<T> findFromReference();
}
