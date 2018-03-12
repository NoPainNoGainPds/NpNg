package utils.daoUtils;

import model.Fournisseur;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FournisseurDAO extends DAO<Fournisseur> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(FournisseurDAO.class);
    public FournisseurDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Fournisseur obj) {
        return false;
    }

    @Override
    public boolean delete(Fournisseur obj) {
        return false;
    }

    @Override
    public boolean update(Fournisseur obj) {
        return false;
    }

    @Override
    public Fournisseur find(int id) {
        return null;
    }

    @Override
    public ArrayList<Fournisseur> findFromReference(int id) {
        return null;
    }

    @Override
    public ArrayList<Fournisseur> findFromReference() {
        try
        {
            ArrayList<Fournisseur> list = new ArrayList<>();
            ResultSet res = null;
            while(res.next())
            {
                list.add(new Fournisseur());
            }
            logger.info("SELECT * FROM FOURNISSEUR");
        }catch(SQLException e)
        {
            logger.error("SQLException");
        }
        return null;
    }
}
