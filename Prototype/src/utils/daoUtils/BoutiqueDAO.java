package utils.daoUtils;

import model.Boutique;
import model.Produit;
import utils.Constants;
import utils.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoutiqueDAO extends DAO<Boutique> {
    public BoutiqueDAO()
    {
        super(Constants.DB.getConnection());
    }
    @Override
    public boolean create(Boutique obj) {
        return false;
    }

    @Override
    public boolean delete(Boutique obj) {
        return false;
    }

    @Override
    public boolean update(Boutique obj) {
        return false;
    }

    @Override
    public Boutique find(int id) {
        try
        {
            Statement stmt =  this.connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Boutique WHERE idBoutique ="+id+";");
            if(res.first())
            {
                Boutique b = new Boutique(res.getInt("idBoutique"),res.getString("nom_boutique"));
                return b;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
