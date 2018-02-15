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
        try
        {
            int idEmplacement = -1;
            if(obj.getEmplacement()!=null)
                idEmplacement = obj.getEmplacement().getId();
            else
                return false;
            String requete = "INSERT INTO boutique (nom_boutique,id_boutique) VALUES ("+obj.getNom()+","+obj.getCategorieBoutique().getId()+") ";
            Statement stmt = Constants.DB.getConnection().createStatement();
            stmt.executeUpdate(requete);
            if(obj.getEmplacement()!=null) {
                idEmplacement = obj.getEmplacement().getId();
                String requete2 = "REPLACE INTO emplacement_boutique (id_emplacement,id_boutique) VALUES ("+ idEmplacement + "," + obj.getId() + ") ";
                Statement stmt2 = Constants.DB.getConnection().createStatement();
                stmt2.executeQuery(requete2);
            }
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Boutique obj) {
        return false;
    }

    @Override
    public boolean update(Boutique obj) {
        try
        {
            int idEmplacement = -1;
            if(obj.getEmplacement()!=null)
                idEmplacement = obj.getEmplacement().getId();
            else
                return false;
            String requete = "UPDATE boutique SET nom_boutique=\""+obj.getNom()+"\" ,categorie="+obj.getCategorieBoutique().getId()+" WHERE idBoutique = "+obj.getId()+";";
            Statement stmt = Constants.DB.getConnection().createStatement();
            stmt.executeUpdate(requete);
            if(obj.getEmplacement()!=null) {
                idEmplacement = obj.getEmplacement().getId();
                String requete2 = "REPLACE INTO emplacement_boutique (id_emplacement,id_boutique) VALUES ("+ idEmplacement + "," + obj.getId() + ") ";
                Statement stmt2 = Constants.DB.getConnection().createStatement();
                stmt2.executeQuery(requete2);
            }
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boutique find(int id) {
        try
        {
            Statement stmt =  this.connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Boutique WHERE idBoutique ="+id+";");
            if(res.first())
            {
                Boutique b = new Boutique(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4));
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
            String requete = "SELECT id_boutique,nom_boutique,id_categorie_boutique,id_emplacement FROM boutique";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Boutique> listBoutique = new ArrayList<>();

            while(res.next())
            {
                listBoutique.add(new Boutique(res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),res.getInt("id_emplacement")));
            }
            return listBoutique;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<Boutique> findFromReference() {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT id_boutique,nom_boutique,id_categorie_boutique,id_emplacement FROM boutique";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Boutique> listBoutique = new ArrayList<>();

            while(res.next())
            {
                listBoutique.add(new Boutique(res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),res.getInt("id_emplacement")));
            }
            return listBoutique;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
