package utils.daoUtils;

import model.Boutique;
import controller.Client;
import model.CategorieBoutique;
import model.Emplacement;
import model.Profil;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
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

    private Client client;

    /**
     * Constructor.
     */
    public BoutiqueDAO(Connection con,Client client) {
        super(con);
        this.client = client;
    }

    public BoutiqueDAO(Connection con) {
        super(con);
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
            if(obj.getNom() != null || obj.getNom()!="")
                nom = obj.getNom();
            else
                return false;
            String requete = "INSERT INTO boutique (nom_boutique,id_categorie_boutique,id_emplacement) VALUES (\""+nom+"\","+catBoutique+","+idEmplacement+")";
            Statement stmt = this.connection.createStatement();
            logger.info(requete);
            return (stmt.executeUpdate(requete)>0) ? true : false;
        }catch(SQLException e)
        {
            e.printStackTrace();
            logger.error(e.toString());
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
            Statement stmt = this.connection.createStatement();
            stmt.executeUpdate(requete);
            logger.info(requete);
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            logger.error(e.toString());
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
        return false;
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
                //Boutique b = new Boutique(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4),res.getString(5));
                //logger.info("SELECT * FROM Boutique WHERE id_boutique ="+id+";");
                //return b;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error(e.toString());
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
            String requete = "SELECT id_boutique,nom_boutique,id_categorie_boutique,id_emplacement,url_logo FROM boutique";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Boutique> listBoutique = new ArrayList<>();

            while(res.next())
            {
                //listBoutique.add(new Boutique(res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),res.getInt("id_emplacement"),res.getString("url_logo")));
            }
            logger.info(requete);
            return listBoutique;
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error(e.toString());
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
            String requete = "SELECT id_boutique,nom_boutique,id_categorie_boutique,id_emplacement,url_logo FROM boutique";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Boutique> listBoutique = new ArrayList<>();
            while(res.next())
            {
                listBoutique.add(new Boutique(this.client,res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),res.getInt("id_emplacement"),res.getString("url_logo")));
            }
            stmt.close();
            logger.info(requete);
            return listBoutique;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }
    public Integer[]  findWhoSale(String productName)
    {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT id_boutique FROM entree_stock es JOIN produit p ON es.id_produit = p.id_produit WHERE p.nom_produit like  '%"+productName+"%'; ";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Integer> returnArray = new ArrayList<>();
            while(res.next())
            {
                returnArray.add(new Integer(res.getInt("id_boutique")));
            }
            logger.info(requete);
            return returnArray.toArray(new Integer[returnArray.size()]);
        }catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
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
            logger.info(requete);
        } catch(SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return nb_boutiques;
    }

    /**
     * Method to unlocate a store
     * @param store the store to unlocate
     */
    public void unlocate(Boutique store) {
        try {
            String query = "update Boutique set located=false where id_boutique="+store.getId()+";";
            Statement stmt = this.connection.createStatement();
            stmt.executeUpdate(query);
            logger.info(query);
        } catch(SQLException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Method to find the stores which have to pay
     * @return A list of the stores.
     */
    public ArrayList<Boutique> findWhoPay() {
        try {
            Statement stmt = this.connection.createStatement();
            String requete =  "select b.id_boutique, e.nom_emplacement, e.id_emplacement, e.superficie, c.nom_categorie_emplacement from boutique b, emplacement e, categorie_emplacement c where b.id_emplacement=e.id_emplacement and e.id_categorie_emplacement=c.id_categorie_emplacement and b.id_emplacement != -1";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList list = new ArrayList();

            while(res.next()) {
                list.add(new Boutique (res.getInt("id_boutique"), new Emplacement(res.getString("nom_emplacement"), res.getInt("id_emplacement"), res.getInt("superficie"), res.getString("nom_categorie_emplacement"))));

            }

            this.logger.info(requete);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            this.logger.error("SQLException");
            return null;
        }
    }

    /**
     * Method to select all stores in database with the store's category
     * @return Stores list
     */
    public ArrayList<Boutique> getStoreWithCategory() {
        ArrayList<Boutique> liste = new ArrayList<>();

        try {
            Statement s = this.connection.createStatement();
            String requete = "SELECT id_boutique, nom_categorie_boutique FROM boutique as b , categorie_boutique as l WHERE b.id_categorie_boutique = l.id_categorie_boutique";
            ResultSet r = s.executeQuery(requete);

            while(r.next()) {
                System.out.println("id :" + r.getInt("id_boutique"));
                Boutique b  = new Boutique(r.getInt("id_boutique"),new CategorieBoutique(r.getString("nom_categorie_boutique"), 0));
                liste.add(b);
                System.out.println("Store :" + b);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(liste.toString());
        return liste;

    }
}


