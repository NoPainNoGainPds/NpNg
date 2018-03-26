package utils.daoUtils;

import model.StockEntree;
import model.StockSortie;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.util.ArrayList;

public class StockEntreeDAO extends DAO<StockEntree>{

        //Log after any action in the CRUD
        private Logger logger = Logger.getLogger(utils.daoUtils.StockEntreeDAO.class);
        public StockEntreeDAO()
        {
            super(Constants.conServ);
        }

        @Override
        public boolean create(StockEntree obj) {
            return false;
        }

        @Override
        public boolean delete(StockEntree obj) {
            return false;
        }

        @Override
        public boolean update(StockEntree obj) {
            return false;
        }

        @Override
        public StockEntree find(int id) {
            return null;
        }

        @Override
        public ArrayList<StockEntree> findFromReference(int id) {
            return null;
        }
        @Override
        public ArrayList<StockEntree> findFromReference() {
            return null;
        }

        public ArrayList<StockEntree> findFromReference(int id_produit, int id_boutique){
            ArrayList<StockEntree> liste = new ArrayList<>();
            this.connection.send("{\"name\" : \"StockEntree\",\"id\":"+id_produit+", \"ref\":"+id_boutique+"}");
            boolean recieved = false;
            while(!recieved)
            {
                Object p = (Object)this.connection.recieve(StockEntree.class);
                if (p != null) {
                    liste.add((StockEntree) p);
                } else {
                    recieved = true;
                }
            }
            return liste;
        }
}



