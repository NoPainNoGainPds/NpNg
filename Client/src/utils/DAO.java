package utils;

import java.lang.reflect.Array;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
    protected ConnectionServer connection;
    public DAO(ConnectionServer connection)
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

    /**
     * Methode de recherche de toute la table
     * grace a une cle etrangere.
     *
     * @param id
     * @return ArrayList<T>
     */
    public abstract ArrayList<T> findFromReference(int id);
    public abstract ArrayList<T> findFromReference();
}
