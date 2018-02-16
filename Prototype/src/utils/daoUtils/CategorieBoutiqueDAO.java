package utils.daoUtils;
import org.apache.log4j.Logger;

import model.CategorieBoutique;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategorieBoutiqueDAO extends DAO<CategorieBoutique> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(CategorieBoutiqueDAO.class);
    public CategorieBoutiqueDAO() {
        super(Constants.DB.getConnection());
    }

    @Override
    public boolean create(CategorieBoutique obj) {
        return false;
    }

    @Override
    public boolean delete(CategorieBoutique obj) {
        return false;
    }

    @Override
    public boolean update(CategorieBoutique obj) {
        return false;
    }

    @Override
    public CategorieBoutique find(int id) {
        return null;
    }

    @Override
    public ArrayList<CategorieBoutique> findFromReference(int id) {
        return null;
    }

    @Override
    public ArrayList<CategorieBoutique> findFromReference() {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT id_categorie_boutique,nom FROM categorie_boutique";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<CategorieBoutique> listBoutique = new ArrayList<>();

            while(res.next())
            {
                listBoutique.add(new CategorieBoutique(res.getString(2),res.getInt(1)));
            }
            logger.info(requete);
            return listBoutique;
        } catch (SQLException e) {
            logger.error("SQLException");
        }
        return null;
    }
}
