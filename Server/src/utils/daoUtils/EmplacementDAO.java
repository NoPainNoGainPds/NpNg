package utils.daoUtils;

import controller.Client;
import model.Emplacement;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.awt.*;
import java.sql.Connection;
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
    public EmplacementDAO(Connection con)
    {
        super(con);
    }

    /**
     * Add a new location to the database.
     * @param obj The location to add.
     * @return true if it works, false else.
     */
    @Override
    public boolean create(Emplacement obj) {
        return false;
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
        try
        {
            String requete = "SELECT id_emplacement,nom_emplacement,superficie,position,nom_categorie_emplacement FROM emplacement " +
                    "join categorie_emplacement on categorie_emplacement.id_categorie_emplacement=emplacement.id_categorie_emplacement" +
                    " where emplacement.id_emplacement = "+id+";";
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            if(res.first())
            {
                return new Emplacement(res.getString("nom_emplacement"),id,res.getInt("superficie"),res.getString("nom_categorie_emplacement"));
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all the locations from the database.
     * @param id
     * @return a list of the locations.
     */
    @Override
    public ArrayList<Emplacement> findFromReference(int id) {
        try
        {
            String requete = "SELECT id_emplacement,categorie_emplacement.nom_categorie_emplacement FROM emplacement join categorie_emplacement ON emplacement.id_emplacement = categorie_emplacement.id_emplacement where id_boutique ="+id;
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Emplacement> list = new ArrayList<>();
            while(res.next())
            {
                list.add(new Emplacement(res.getString("nom_emplacement"),res.getInt("id_emplacement"),res.getInt("superficie"),res.getString("nom_categorie_emplacement")));
            }
            logger.info(requete);
            return list;
        }catch (SQLException e) {
            logger.error("SQLException");
            return null;
        }
    }
    /**
     * Get all the locations from the database.
     * @return a list of the locations.
     */
    @Override
    public ArrayList<Emplacement> findFromReference(Client c) {
        try
        {
            String requete = "SELECT id_emplacement,nom_emplacement,superficie,position,nom_categorie_emplacement FROM emplacement " +
                    "join categorie_emplacement on categorie_emplacement.id_categorie_emplacement=emplacement.id_categorie_emplacement" +
                    " ;";
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Emplacement> list = new ArrayList<>();
            while(res.next())
            {
                list.add(new Emplacement(res.getString("nom_emplacement"),res.getInt("id_emplacement"),res.getInt("superficie"),res.getString("nom_categorie_emplacement")));
            }
            logger.info(requete);
            return list;
        }catch (SQLException e) {
            logger.error("SQLException");
            return null;
        }
    }
    public ArrayList<Point> getPathLocation(int idLocation)
    {
        try
        {
            String query = "Select coord_x,coord_y from coordonnees_emplacement where id_emplacement ="+idLocation+";";
            ArrayList<Point> liste = new ArrayList<>();
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next())
            {
                int x = res.getInt(1);
                int y= res.getInt(2);
                liste.add(new Point(x,y));
            }
            return liste;
        }catch(SQLException e)
        {
            logger.error(e);
            return null;
        }
    }
}
