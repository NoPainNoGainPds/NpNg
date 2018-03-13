package utils.daoUtils;
import org.apache.log4j.Logger;
import model.Produit;
import utils.ConnectionServer;
import utils.Constants;
import utils.DAO;

import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class which represents a product. it contains the methods which access the database.
 */
public class ProduitDAO extends DAO<Produit> {

    /**
     * A logger. Use to have a trace of what happen during the execution.
     */
    private Logger logger = Logger.getLogger(ProduitDAO.class);

    /**
     * Constructor.
     */
    public ProduitDAO(ConnectionServer con)
    {
        super(con);
    }

    /**
     * Add a new product to the database.
     * @param obj the product to add.
     * @return true if it works, false else.
     */
    @Override
    public boolean create(Produit obj) {
        return false;
    }

    /**
     * Delete a product from the database.
     * @param obj The product to delete.
     * @return true if it works, false else.
     */
    @Override
    public boolean delete(Produit obj) {
        return false;
    }

    /**
     * Update a product from the database.
     * @param obj The product to modify.
     * @return true if it works, false else.
     */
    @Override
    public boolean update(Produit obj) {
        return false;
    }

    /**
     * Find a product in the database.
     * @param id The product's id.
     * @return the product found.
     */
    @Override
    public Produit find(int id) {

        return null;
    }

    /**
     * Get all the products from a store in the database.
     * @param id The store's id
     * @return A list of the products.
     */
    @Override
    public ArrayList<Produit> findFromReference(int id)
    {

        return null;
    }
    /**
     * Get all the products from the database.
     * @return A list of the products.
     */
    public ArrayList<Produit> findFromReference()
    {

        return null;
    }

}
