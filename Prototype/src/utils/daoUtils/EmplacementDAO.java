package utils.daoUtils;

import model.Emplacement;
import utils.Constants;
import utils.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmplacementDAO extends DAO<Emplacement> {
    public EmplacementDAO()
    {
        super(Constants.DB.getConnection());
    }
    @Override
    public boolean create(Emplacement obj) {
        return false;
    }

    @Override
    public boolean delete(Emplacement obj) {
        return false;
    }

    @Override
    public boolean update(Emplacement obj) {
        return false;
    }

    @Override
    public Emplacement find(int id) {
        return null;
    }

    @Override
    public ArrayList<Emplacement> findFromReference(int id) {
        try
        {
            String requete = "SELECT idEmplacement,emplacement.nom FROM Emplacement_boutique join emplacement ON emplacement_boutique.id_Emplacement = emplacement.idEmplacement where id_boutique ="+id;
            Statement stmt = Constants.DB.getConnection().createStatement();
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Emplacement> list = new ArrayList<>();
            while(res.next())
            {
                list.add(new Emplacement(res.getString("nom"),res.getInt("idEmplacement")));
            }
            return list;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ArrayList<Emplacement> findFromReference() {
        try
        {
            String requete = "SELECT idEmplacement,nom FROM Emplacement";
            Statement stmt = Constants.DB.getConnection().createStatement();
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Emplacement> list = new ArrayList<>();
            while(res.next())
            {
                list.add(new Emplacement(res.getString("nom"),res.getInt("idEmplacement")));
            }
            return list;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
