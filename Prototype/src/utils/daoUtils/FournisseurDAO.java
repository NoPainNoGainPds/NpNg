package utils.daoUtils;
import org.apache.log4j.Logger;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import model.Fournisseur;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FournisseurDAO extends DAO<Fournisseur> {
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
            ResultSet res = Constants.DB.getValueTable("Fournisseur");
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