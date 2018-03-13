package utils.daoUtils;

import controller.Client;
import model.StockSortie;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.util.ArrayList;

public class StockSortieDAO extends DAO<StockSortie> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(StockSortieDAO.class);
    public StockSortieDAO(Connection con)
    {
        super(con);
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
    public ArrayList<StockSortie> findFromReference(Client c) {
        return null;
    }
}
