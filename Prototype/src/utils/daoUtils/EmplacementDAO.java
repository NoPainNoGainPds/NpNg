package utils.daoUtils;
import org.apache.log4j.Logger;
import model.Emplacement;
import utils.Constants;
import utils.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmplacementDAO extends DAO<Emplacement> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(EmplacementDAO.class);
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
            String requete = "SELECT id_emplacement,categorie_emplacement.nom_categorie_emplacement FROM emplacement join categorie_emplacement ON emplacement.id_emplacement = categorie_emplacement.id_emplacement where id_boutique ="+id;
            Statement stmt = Constants.DB.getConnection().createStatement();
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Emplacement> list = new ArrayList<>();
            while(res.next())
            {
                list.add(new Emplacement(res.getString("nom_emplacement"),res.getInt("id_emplacement")));
            }
            logger.info(requete);
            return list;
        }catch (SQLException e) {
            logger.error("SQLException");
            return null;
        }
    }
    @Override
    public ArrayList<Emplacement> findFromReference() {
        try
        {
            String requete = "SELECT id_emplacement,categorie_emplacement.nom_categorie_emplacement FROM emplacement, categorie_emplacement";
            Statement stmt = Constants.DB.getConnection().createStatement();
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Emplacement> list = new ArrayList<>();
            while(res.next())
            {
                list.add(new Emplacement(res.getString("nom_emplacement"),res.getInt("id_emplacement")));
            }
            logger.info(requete);
            return list;
        }catch (SQLException e) {
            logger.error("SQLException");
            return null;
        }
    }
}
