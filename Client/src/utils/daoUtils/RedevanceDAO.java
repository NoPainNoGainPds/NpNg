package utils.daoUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Produit;
import model.Redevance;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class which represents a fee. It contains the methods which access the database.
 */
public class RedevanceDAO extends DAO<Redevance> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(utils.daoUtils.RedevanceDAO.class);

    /**
     * Constructor.
     */
    public RedevanceDAO()
    {
        super(Constants.conServ);
    }

    /**
     * Add a new fee in the database.
     * @param obj The fee to add.
     * @return true if it works, false else.
     */
    @Override
    public boolean create(Redevance obj) {
        boolean response;
        String str = "{\"name\" : \"newFee\",\"id\":0}";
        this.connection.send(str);
        ObjectMapper mapper = new ObjectMapper();
        try {
            str = mapper.writeValueAsString(obj);
            this.connection.send(str);
            Object rep =this.connection.recieve(Boolean.class);
        }catch(JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return false;//a changer
    }


    /**
     * Delete a fee from the database.
     * @param obj The fee to delete.
     * @return true if it works, false else.
     */
    @Override
    public boolean delete(Redevance obj) {
        boolean response;
        String str = "{\"name\" : \"deleteFee\",\"id\":"+obj.getid_Redevance()+"}";
        this.connection.send(str);
        ObjectMapper mapper = new ObjectMapper();
            Object rep =this.connection.recieve(Boolean.class);
        return false;//a changer
    }

    /**
     * Update a fee from the database.
     * @param obj The fee to modify.
     * @return true if it works, false else.
     */
    @Override
    public boolean update(Redevance obj) {
        return false;
    }

    /**
     * Find a fee in the database.
     * @param id The fee's id.
     * @return The fee found.
     */
    @Override
    public Redevance find(int id) {
        return null;
    }


    /**
     * Get the fee by its id from the database.
     * @param id the id of the fee
     * @return A list of one fee.
     */
    @Override
    public ArrayList<Redevance> findFromReference(int id) {
        return null;
    }

    /**
     * Get all the fees from the database.
     * @return A list of the fees.
     */
    @Override
    public ArrayList<Redevance> findFromReference() {
        ArrayList<Redevance> liste = new ArrayList<>();
        this.connection.send("{\"name\" : \"Redevance\",\"id\":-1}");
        boolean received = false;
        while(!received)
        {
            Object p = (Object)this.connection.recieve(Redevance.class);
            if (p != null) {
                liste.add((Redevance) p);
            } else {
                received = true;
            }
        }
        return liste;
    }

    /**
     * Get all the fees of the chosen month and year from the database.
     * @param annee the year
     * @param mois the month
     * @return A list of the fees.
     */
    public ArrayList<Redevance> findFromReference(int annee, int mois){
        ArrayList<Redevance> liste = new ArrayList<>();
        this.connection.send("{\"name\" : \"Redevance\",\"id\":"+annee+", \"ref\":"+mois+"}");
        boolean recieved = false;
        while(!recieved)
        {
            Object p = (Object)this.connection.recieve(Redevance.class);
            if (p != null) {
                liste.add((Redevance) p);
            } else {
                recieved = true;
            }
        }
        return liste;
    }

    /**
     * Calculate and assign a fee to all stores
     */
    public void calculFee()
    {

        this.connection.send("{\"name\" : \"Redevance\",\"id\":"+(-2)+", \"ref\":"+(-1)+"}");
    }

    /**
     * Generate a bill in PDF.
     * @param dest name of PDF
     * @param id_redevance id of the fee
     * @return true if it works, false else..
     */
    public boolean generPDF(String dest, int id_redevance ) {
        boolean response;
        String str = "{\"name\" : \"Facture\",\"id\":"+id_redevance+", \"ref\":\"" + dest + "\"}";
        this.connection.send(str);
        ObjectMapper mapper = new ObjectMapper();
        Object rep =this.connection.recieve(String.class);
        //System.out.println(rep);
        try {
            Runtime runTime = Runtime.getRuntime();
            Process process = runTime.exec("\"C:\\Program Files\\Mozilla Firefox\\firefox.exe\" -new-tab \""+rep+"\"");
        }catch (RuntimeException e )
        {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;//a changer
    }
}




