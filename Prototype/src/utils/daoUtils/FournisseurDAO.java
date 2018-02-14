package utils.daoUtils;

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
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
