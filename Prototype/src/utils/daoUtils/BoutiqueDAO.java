package utils.daoUtils;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import model.Boutique;
import model.Produit;
import utils.Constants;
import utils.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        try
        {
            int idEmplacement;
            if(obj.getEmplacement()!=null)
                idEmplacement = obj.getEmplacement().getId();
            String requete = "UPDATE Boutique set ";
            Statement stmt = Constants.DB.getConnection().createStatement();
            return (stmt.executeUpdate(requete))>0 ? true : false;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
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

    @Override
    public ArrayList<Boutique> findFromReference(int id) {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT idBoutique,nom_boutique,categorie FROM boutique";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Boutique> listBoutique = new ArrayList<>();

            while(res.next())
            {
                listBoutique.add(new Boutique(res.getInt("idBoutique"),res.getString("nom_boutique")));
            }
            return listBoutique;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
