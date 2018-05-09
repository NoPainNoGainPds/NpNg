package utils.daoUtils;

import model.CauseSortieStock;
import org.apache.log4j.Logger;
import utils.DAO;
import java.sql.Connection;
import java.util.ArrayList;

public class CauseSortieStockDAO extends DAO<CauseSortieStock> {
    private Logger logger = Logger.getLogger(CauseSortieStockDAO.class);

    public CauseSortieStockDAO(Connection con) {super(con);}

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
    public ArrayList<CauseSortieStock> findFromReference() {
        return null;
    }

    @Override
    public ArrayList<CauseSortieStock> findFromReference(int id) {
        return null;
    }
}
