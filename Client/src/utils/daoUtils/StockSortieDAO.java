package utils.daoUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Produit;
import org.apache.log4j.Logger;
import model.StockSortie;
import utils.Constants;
import utils.DAO;

import java.util.ArrayList;

public class StockSortieDAO extends DAO<StockSortie> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(StockSortieDAO.class);
    public StockSortieDAO()
    {
        super(Constants.conServ);
    }

    @Override
    public boolean create(StockSortie obj) {
        boolean response;
        String str = "{\"name\" : \"CreateStockSortie\",\"id\" : 0}";
        this.connection.send(str);
        ObjectMapper mapper = new ObjectMapper();
        try {
            str = mapper.writeValueAsString(obj);
            this.connection.send(str);
            Object rep = this.connection.recieve(Boolean.class);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        /*this.connection.send("{\"name\" : \"CreateStockSortie\",\"id\" : "+0+"}");
        Boolean b = (Boolean)this.connection.recieve(Boolean.class);

        if (b){
            this.connection.sendObject(obj);
        }
        b = (Boolean)this.connection.recieve(Boolean.class);*/
        return false;

    }

    @Override
    public boolean delete(StockSortie obj) {
        return false;
    }

    @Override
    public boolean update(StockSortie obj) {
        return false;
    }

    @Override
    public StockSortie find(int id) {
        return null;
    }

    @Override
    public ArrayList<StockSortie> findFromReference(int id) {
        return null;
    }
    @Override
    public ArrayList<StockSortie> findFromReference() {
        return null;
    }

    public ArrayList<StockSortie> findFromReference(int id_produit, int id_boutique){
        ArrayList<StockSortie> liste = new ArrayList<>();
        this.connection.send("{\"name\" : \"StockSortie\",\"id\":"+id_produit+", \"ref\":"+id_boutique+"}");
        boolean recieved = false;
        while(!recieved)
        {
            Object p = (Object)this.connection.recieve(StockSortie.class);
            if (p != null) {
                liste.add((StockSortie) p);
            } else {
                recieved = true;
            }
        }
        return liste;
    }
}
