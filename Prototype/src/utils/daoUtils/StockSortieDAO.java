package utils.daoUtils;
import org.apache.log4j.Logger;
import model.StockSortie;
import utils.Constants;
import utils.DAO;

import java.util.ArrayList;

public class StockSortieDAO extends DAO<StockSortie> {
    private Logger logger = Logger.getLogger(StockSortieDAO.class);
    public StockSortieDAO()
    {
        super(Constants.DB.getConnection());
    }

    @Override
    public boolean create(StockSortie obj) {
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
}
