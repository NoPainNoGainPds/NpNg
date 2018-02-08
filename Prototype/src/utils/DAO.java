package utils;

import java.lang.reflect.Array;
import java.sql.Connection;

public abstract class DAO<T> {
    private Connection connection;
    public DAO(Connection connection)
    {
        this.connection = connection;
    }
    public abstract boolean create(T obj);


    /**

     * M�thode pour effacer

     * @param obj

     * @return boolean

     */

    public abstract boolean delete(T obj);


    /**

     * M�thode de mise � jour

     * @param obj

     * @return boolean

     */

    public abstract boolean update(T obj);


    /**

     * M�thode de recherche des informations

     * @param id

     * @return T

     */

    public abstract T find(int id);
}
