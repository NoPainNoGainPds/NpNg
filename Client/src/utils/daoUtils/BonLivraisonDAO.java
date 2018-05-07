package utils.daoUtils;

import model.BonLivraison;
import model.Redevance;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;
import java.util.ArrayList;

public class BonLivraisonDAO extends DAO<BonLivraison> {

    private Logger logger = Logger.getLogger(utils.daoUtils.BonLivraisonDAO.class);

    public BonLivraisonDAO(){
        super(Constants.conServ);
    }
    @Override
    public boolean create(BonLivraison obj){
        return false;
    }
    @Override
    public boolean delete(BonLivraison obj){
        return false;
    }
    @Override
    public boolean update(BonLivraison obj){
        return false;
    }
    @Override
    public BonLivraison find (int id){
        return null;
    }

    @Override
    public ArrayList<BonLivraison> findFromReference(int id) {
        return null;
    }

    public ArrayList<BonLivraison> findFromReference () {
        ArrayList<BonLivraison> liste = new ArrayList<>();
        this.connection.send("{\"name\" : \"Bon de livraison\", \"id\" : -1");
        boolean received = false;
        while(! received){
            Object p = (Object) this.connection.recieve(BonLivraison.class);
            if (p != null){
                liste.add((BonLivraison) p);
            }else{
                received = true;
            }
        }
        return liste;
    }
}
