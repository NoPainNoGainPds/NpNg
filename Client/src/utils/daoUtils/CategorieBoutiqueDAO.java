package utils.daoUtils;
import org.apache.log4j.Logger;

import model.CategorieBoutique;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class which represents a store category. It contains the methods which access the database.
 */
public class CategorieBoutiqueDAO extends DAO<CategorieBoutique> {

    /**
     * A logger. Use to have a trace of what happen during the execution.
     */
    private Logger logger = Logger.getLogger(CategorieBoutiqueDAO.class);

    /**
     * Constructor.
     */
    public CategorieBoutiqueDAO() {
        super(Constants.conServ);
    }

    /**
     * Add a new store category in the database.
     * @param obj the store category to add.
     * @return true if it works, false else.
     */
    @Override
    public boolean create(CategorieBoutique obj) {
        return false;
    }

    /**
     * Delete a store category from the database.
     * @param obj The store category to delete.
     * @return true if it works, false else.
     */
    @Override
    public boolean delete(CategorieBoutique obj) {
        return false;
    }

    /**
     * Update a store category from the database.
     * @param obj the store category to modify.
     * @return true if it works, false else.
     */
    @Override
    public boolean update(CategorieBoutique obj) {
        return false;
    }

    /**
     * Find a store category in the database.
     * @param id The store category's id.
     * @return The store category found.
     */
    @Override
    public CategorieBoutique find(int id) {

        return null;
    }

    @Override
    public ArrayList<CategorieBoutique> findFromReference(int id) {
        return null;
    }

    /**
     * Get all the store categories from the database.
     * @return A list of the store gategories.
     */
    @Override
    public ArrayList<CategorieBoutique> findFromReference() {

        return null;
    }
}
