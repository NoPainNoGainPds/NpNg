package utils.daoUtils;


import model.BonLivraison;
import org.apache.log4j.Logger;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BonLivraisonDAO extends DAO<BonLivraison>{

    private Logger logger = Logger.getLogger(BonLivraisonDAO.class);
    public BonLivraisonDAO(Connection con)
    {
        super(con);
    }

    @Override
    public boolean create(BonLivraison obj) {
        return false;
    }

    @Override
    public boolean delete(BonLivraison obj) {
        return false;
    }

    @Override
    public boolean update(BonLivraison obj) {
        return false;
    }

    @Override
    public BonLivraison find(int id) {
        return null;
    }

    @Override
    public ArrayList<BonLivraison> findFromReference() {
        return null;
    }

    @Override
    public ArrayList<BonLivraison> findFromReference(int id) {
        return null;
    }

}
