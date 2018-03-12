package utils;

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

     * Méthode pour effacer

     * @param obj

     * @return boolean

     */

    public abstract boolean delete(T obj);


    /**

     * Méthode de mise à jour

     * @param obj

     * @return boolean

     */

    public abstract boolean update(T obj);


    /**

     * Méthode de recherche des informations

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
