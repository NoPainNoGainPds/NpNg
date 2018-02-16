package utils.daoUtils;
import org.apache.log4j.Logger;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import model.Boutique;
import model.Produit;
import utils.Constants;
import utils.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class which represents a store. It contains the methods which access the database.
 */
public class BoutiqueDAO extends DAO<Boutique> {
    /**
     * A logger. Use to have a trace of what happen during the execution.
     */
    private Logger logger = Logger.getLogger(BoutiqueDAO.class);

    /**
     * Constructor.
     */
    public BoutiqueDAO() {
        super(Constants.DB.getConnection());
    }

    /**
     * Add a new store in the database.
     * @param obj The store to add.
     * @return true if it works, false else.
     */
    @Override
    public boolean create(Boutique obj) {
        try
        {
            int idEmplacement = -1;
            int catBoutique = -1;
            String nom = "";
            if(obj.getEmplacement()!=null)
                idEmplacement = obj.getEmplacement().getId();
            else
                return false;
            if(obj.getCategorieBoutique()!=null)
                catBoutique = obj.getCategorieBoutique().getId();
            else
                return false;
            if(obj.getNom() != null || obj.getNom()!="")
                nom = obj.getNom();
            else
                return false;
            String requete = "INSERT INTO boutique (nom_boutique,id_categorie_boutique,id_emplacement) VALUES (\""+nom+"\","+catBoutique+","+idEmplacement+")";
            Statement stmt = Constants.DB.getConnection().createStatement();

            return (stmt.executeUpdate(requete)>0) ? true : false;
        }catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("SQLException");
            return false;
        }
    }

    /**
     * Delete a store from the database.
     * @param obj The store to delete.
     * @return true if it works, false else.
     */
    @Override
    public boolean delete(Boutique obj) {
        try
        {
            String requete = "DELETE FROM Boutique where id_boutique="+obj.getId()+";";
            Statement stmt = Constants.DB.getConnection().createStatement();
            stmt.executeUpdate(requete);

            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("SQLException");
            return false;
        }
    }

    /**
     * Update a store from the database.
     * @param obj The store to modify.
     * @return true if it works, false else.
     */
    @Override
    public boolean update(Boutique obj) {
        try
        {
            int idEmplacement = -1;
            if(obj.getEmplacement()!=null)
                idEmplacement = obj.getEmplacement().getId();
            else
                return false;
            String requete = "UPDATE boutique SET nom_boutique=\""+obj.getNom()+"\" ,id_categorie_boutique="+obj.getCategorieBoutique().getId()+" WHERE id_boutique = "+obj.getId()+";";
            Statement stmt = Constants.DB.getConnection().createStatement();
            stmt.executeUpdate(requete);
            if(obj.getEmplacement()!=null) {
                idEmplacement = obj.getEmplacement().getId();
                String requete2 = "REPLACE INTO boutique (id_emplacement,id_boutique) VALUES ("+ idEmplacement + "," + obj.getId() + ") ";
                Statement stmt2 = Constants.DB.getConnection().createStatement();
                stmt2.executeQuery(requete2);
            }
            logger.info(requete);
            return true;
        }catch(SQLException e)
        {
            //e.printStackTrace();
            logger.error("SQLException");
            return false;
        }
    }

    /**
     * Find a store in the database.
     * @param id The store's id.
     * @return The store found.
     */
    @Override
    public Boutique find(int id) {
        try
        {
            Statement stmt =  this.connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Boutique WHERE id_boutique ="+id+";");
            if(res.first())
            {
                Boutique b = new Boutique(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4));
                //logger.info("SELECT * FROM Boutique WHERE id_boutique ="+id+";");
                return b;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("SQLException");
        }
        return null;
    }

    /**
     * Get all the stores from the database.
     * @param id
     * @return A list of the stores.
     */
    @Override
    public ArrayList<Boutique> findFromReference(int id) {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT id_boutique,nom_boutique,id_categorie_boutique,id_emplacement FROM boutique";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Boutique> listBoutique = new ArrayList<>();

            while(res.next())
            {
                listBoutique.add(new Boutique(res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),res.getInt("id_emplacement")));
            }
            logger.info(requete);
            return listBoutique;
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("SQLException");
        }
        return null;
    }

    /**
     * get all the stores from the database.
     * @return A list of te stores.
     */
    @Override
    public ArrayList<Boutique> findFromReference() {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT id_boutique,nom_boutique,id_categorie_boutique,id_emplacement FROM boutique";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Boutique> listBoutique = new ArrayList<>();

            while(res.next())
            {
                listBoutique.add(new Boutique(res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),res.getInt("id_emplacement")));
            }
            logger.info(requete);
            return listBoutique;
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("SQLException");
        }
        return null;
    }

    /**
     * Count the number of stores in the database
     * @return The number of stores
     */
    public int getNbBoutiques() {
        int nb_boutiques = 0;
        try {
            Statement stmt = this.connection.createStatement();
            String requete = "SELECT id_boutique FROM boutique";
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()) {
                nb_boutiques++;
            }
        } catch(SQLException e) {
            logger.error("SQLException");
        }
        return nb_boutiques;
    }
}
