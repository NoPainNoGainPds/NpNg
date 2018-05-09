package utils.daoUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Produit;
import model.Redevance;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.util.ArrayList;


public class RedevanceDAO extends DAO<Redevance> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(utils.daoUtils.RedevanceDAO.class);
    public RedevanceDAO()
    {
        super(Constants.conServ);
    }

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



    @Override
    public boolean delete(Redevance obj) {
        boolean response;
        String str = "{\"name\" : \"deleteFee\",\"id\":"+obj.getid_Redevance()+"}";
        this.connection.send(str);
        ObjectMapper mapper = new ObjectMapper();
            Object rep =this.connection.recieve(Boolean.class);
        return false;//a changer
    }

    @Override
    public boolean update(Redevance obj) {
        return false;
    }

    @Override
    public Redevance find(int id) {
        return null;
    }

    @Override
    public ArrayList<Redevance> findFromReference(int id) {
        return null;
    }
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
    public void calculFee()
    {
        this.connection.send("{\"name\" : \"Redevance\",\"id\":"+(-2)+", \"ref\":"+(-1)+"}");
    }

}




