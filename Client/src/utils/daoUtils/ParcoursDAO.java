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
     * Add a new course to the database.
     * @param obj The course to add.
     * @return true if it works, false else.
     */
    public boolean create(Parcours obj) {
        return false;
    }

    /**
     * Delete a course from the database.
     * @param obj the course to delete.
     * @return true if it works, false else.
     */
    public boolean delete(Parcours obj) {
        return false;
    }

    /**
     * Update a courrse from the database.
     * @param obj The course to modify.
     * @return true if it works, false else.
     */
    public boolean update(Parcours obj) {
        return false;
    }

    /**
     * Find a course in the database.
     * @param idParcours The course's id.
     * @return the course found.
     */
    public Parcours find(int idParcours) {

        return null;
    }

    public ArrayList<Parcours> findFromReference(int id) {
        return null;
    }

    public ArrayList<Parcours> findFromReference() {
        return null;
    }
}
