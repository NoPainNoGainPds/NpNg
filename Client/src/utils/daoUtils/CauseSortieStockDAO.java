package utils.daoUtils;

import model.CauseSortieStock;
import utils.DAO;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;
import java.util.ArrayList;

public class CauseSortieStockDAO extends DAO<CauseSortieStock> {
    private Logger logger = Logger.getLogger(utils.daoUtils.CauseSortieStockDAO.class);

    public CauseSortieStockDAO(){
        super(Constants.conServ);
    }

    @Override
    public boolean create(CauseSortieStock obj) {
        return false;
    }

    @Override
    public boolean delete(CauseSortieStock obj) {
        return false;
    }

    @Override
    public boolean update(CauseSortieStock obj) {
        return false;
    }

    @Override
    public CauseSortieStock find(int id) {
        return null;
    }

    @Override
    public ArrayList<CauseSortieStock> findFromReference(int id) {
        return null;
    }

    @Override
    public ArrayList<CauseSortieStock> findFromReference() {
        return null;
    }
}
