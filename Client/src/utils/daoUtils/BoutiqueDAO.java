package utils.daoUtils;
import model.Profile;
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
    public BoutiqueDAO()
    {
        super(Constants.conServ);
    }
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
    public Integer[] findWhoSale(String productName)
    {
        try
        {
            //envoie du message au serv
            String str = "{\"name\":\"Store\",\"id\":-2,\"ref\":\""+productName+"\"}";
            this.connection.send(str);
            ArrayList<Integer> list = new ArrayList<>();
            boolean recieved = false;
            while(!recieved)
            {
                Object val = (Object)this.connection.recieve(Integer.class);
                System.out.println(val);
                if(val!=null)
                {
                    list.add((Integer)val);
                }else
                {
                    recieved = true;
                }
            }
            return list.toArray(new Integer[list.size()]);
        }catch(Exception e)
        {
            e.printStackTrace();
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
            //envoie du message au serv
            String str = "{\"name\":\"Store\",\"id\":-1}";
            this.connection.send(str);

            ArrayList<Boutique> liste = new ArrayList<>();
            boolean recieved = false;
            while(!recieved)
            {
                Object b = (Object)this.connection.recieve(Boutique.class);
                if (b != null) {
                    Boutique b2 = (Boutique) b;
                    liste.add(b2);
                } else {
                    recieved = true;
                }
            }
            return liste;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to find the stores which have to pay
     * @return A list of the stores.
     */
    public ArrayList<Boutique> findWhoPay() {
        try
        {
            //envoie du message au serv
            String str = "{\"name\":\"Store\",\"id\":-3}";
            this.connection.send(str);

            ArrayList<Boutique> liste = new ArrayList<>();
            boolean recieved = false;
            while(!recieved)
            {
                Object b = (Object)this.connection.recieve(Boutique.class);
                if (b != null) {
                    Boutique b2 = (Boutique) b;
                    liste.add(b2);
                } else {
                    recieved = true;
                }
            }
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

    /**
     * Get all the stores with store's category in the database
     * @return Stores List
     */
    public ArrayList<Boutique> getStoreWithCategory(){
        try{
            String str = "{\"name\":\"SearchStoreWithCategory\",\"id\":"+0+"}\n";
            this.connection.send(str);
            ArrayList<Boutique> liste = new ArrayList<>();
            boolean recieved = false;
            while(!recieved)
            {
                Boutique b = (Boutique) this.connection.recieve(Boutique.class);
                if (b != null) {
                    liste.add(b);
                } else {
                    recieved = true;
                }
            }
            System.out.println(liste.toString());
            return liste;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
