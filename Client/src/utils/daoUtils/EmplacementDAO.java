package utils.daoUtils;
import org.apache.log4j.Logger;
import model.Emplacement;
import utils.ConnectionServer;
import utils.Constants;
import utils.DAO;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Class which represents a location. It contains the methods which access the database.
 */
public class EmplacementDAO extends DAO<Emplacement> {

    /**
     * A logger. Use to have a trace of what happen during the execution.
     */
    private Logger logger = Logger.getLogger(EmplacementDAO.class);

    /**
     * Constructor.
     */
    public EmplacementDAO()
    {
        super(Constants.conServ);
    }

    /**
     * Add a new location to the database.
     * @param obj The location to add.
     * @return true if it works, false else.
     */
    @Override
    public boolean create(Emplacement obj) {
        try {
            String str ="{\"name\":\"CreateLocation\",\"id\":0}";
            this.connection.send(str);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a location from the database.
     * @param obj the location to delete.
     * @return true if it works, false else.
     */
    @Override
    public boolean delete(Emplacement obj) {
        return false;
    }

    /**
     * Update a location from the database.
     * @param obj The location to modify.
     * @return true if it works, false else.
     */
    @Override
    public boolean update(Emplacement obj) {
        return false;
    }

    /**
     * Find a location in the database.
     * @param id The location's id.
     * @return the location found.
     */
    @Override
    public Emplacement find(int id) {

        return null;
    }

    /**
     * Get all the locations from the database.
     * @param id
     * @return a list of the locations.
     */
    @Override
    public ArrayList<Emplacement> findFromReference(int id) {
        return null;
    }
    /**
     * Get all the locations from the database.
     * @return a list of the locations.
     */
    @Override
    public ArrayList<Emplacement> findFromReference() {
        return null;
    }
    public ArrayList<Point> getPathLocation(int idLocation)
    {
        return null;
    }
    public void assignLocationsToStores() {
        try {
            String str ="{\"name\":\"AssignLocationsToStores\",\"id\":0}";
            this.connection.send(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unassignAllLocations() {
        try {
            String str ="{\"name\":\"UnassignAllLocations\",\"id\":0}";
            this.connection.send(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
