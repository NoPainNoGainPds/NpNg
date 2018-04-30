package utils.daoUtils;

import model.Emplacement;
import model.Parcours;
import org.apache.log4j.Logger;
import utils.ConnectionServer;
import utils.Constants;
import utils.DAO;

import java.util.ArrayList;


public class ParcoursDAO extends DAO<Parcours> {

    private Logger logger = Logger.getLogger(ParcoursDAO.class);

    public ParcoursDAO()
    {
        super(Constants.conServ);
    }

    /**
     * Add a new location to the database.
     * @param obj The location to add.
     * @return true if it works, false else.
     */
    @Override
    public boolean create(Parcours obj) {
        return false;
    }

    /**
     * Delete a location from the database.
     * @param obj the location to delete.
     * @return true if it works, false else.
     */
    @Override
    public boolean delete(Parcours obj) {
        return false;
    }

    /**
     * Update a location from the database.
     * @param obj The location to modify.
     * @return true if it works, false else.
     */
    @Override
    public boolean update(Parcours obj) {
        return false;
    }

    /**
     * Find a location in the database.
     * @param idParcours The location's id.
     * @return the location found.
     */
    @Override
    public Parcours find(int idParcours) {

        return null;
    }

    @Override
    public ArrayList<Parcours> findFromReference(int id) {
        return null;
    }

    @Override
    public ArrayList<Parcours> findFromReference() {
        return null;
    }
}
