package utils.daoUtils;

import model.Produit;
import utils.Constants;
import utils.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ProduitDAO extends DAO<Produit> {
    public ProduitDAO()
    {
        super(Constants.DB.getConnection());
    }
    @Override
    public boolean create(Produit obj) {
        return false;
    }

    @Override
    public boolean delete(Produit obj) {
        return false;
    }

    @Override
    public boolean update(Produit obj) {
        return false;
    }

    @Override
    public Produit find(int id) {
        try
        {
            Statement stmt =  this.connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM PRODUIT WHERE idProduit ="+id+";");
            if(res.first())
            {
                Produit p = new Produit(res.getString("nom"),res.getInt("idProduit"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
