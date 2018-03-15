package utils.daoUtils;
import org.apache.log4j.Logger;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import model.Boutique;
import model.Produit;
import utils.ConnectionServer;
import utils.Constants;
import utils.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents a store. It contains the methods which access the database.
 */
public class BoutiqueDAO extends DAO<Boutique> {
    /**
     * A logger. Use to have a trace of what happen during the execution.
     */

    /**
     * Constructor.
     */
    public BoutiqueDAO(ConnectionServer con) {
        super(con);
    }

    /**
     * Add a new store in the database.
     * @param obj The store to add.
     * @return true if it works, false else.
     */
    @Override
    public boolean create(Boutique obj) {
        return false;
    }

    /**
     * Delete a store from the database.
     * @param obj The store to delete.
     * @return true if it works, false else.
     */
    @Override
    public boolean delete(Boutique obj) {
        return false;
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
        return null;
    }

    /**
     * Get all the stores from the database.
     * @param id
     * @return A list of the stores.
     */
    @Override
    public ArrayList<Boutique> findFromReference(int id) {
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
            //envoie du message au serv
            String str = "Store:all";
            this.connection.send(str);
            System.out.println("demande effectuée");

            ArrayList<Boutique> liste = new ArrayList<>();
            boolean recieved = false;
            while(!recieved)
            {
                System.out.println("demende");
                Object[] b = (Object[])this.connection.recieve(Boutique.class);
                for(int i =0 ;i< b.length;i++) {
                    Boutique b2 = (Boutique)b[i];
                    if (b2 != null) {
                        liste.add(b2);
                    } else {
                        recieved = true;
                        System.out.println("fin");
                    }
                }
            }
            System.out.println("données recus ");
            return liste;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Count the number of stores in the database
     * @return The number of stores
     */
    public int getNbBoutiques() {
        return 0;
    }
}
