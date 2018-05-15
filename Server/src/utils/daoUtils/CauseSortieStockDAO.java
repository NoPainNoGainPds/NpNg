package utils.daoUtils;

import model.CauseSortieStock;
import org.apache.log4j.Logger;
import utils.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CauseSortieStockDAO extends DAO<CauseSortieStock> {
    private Logger logger = Logger.getLogger(CauseSortieStockDAO.class);

    public CauseSortieStockDAO(Connection con) {super(con);}

    @Override
    public boolean create(CauseSortieStock obj) {return false;}

    @Override
    public boolean delete(CauseSortieStock obj) {
        return false;
    }

    @Override
    public boolean update(CauseSortieStock obj) {
        return false;
    }

    @Override
    public CauseSortieStock find(int id) {
        return null;
    }

    @Override
    public ArrayList<CauseSortieStock> findFromReference() {return null; }

    @Override
    public ArrayList<CauseSortieStock> findFromReference(int id) {
        return null;
    }

    public ArrayList<CauseSortieStock> getCauseSortieStock()
    {
        ArrayList<CauseSortieStock> liste = new ArrayList<>();
        try
        {
            Statement stmt = this.connection.createStatement();
            String requete = "SELECT id_cause_sortie_stock, nom_cause FROM `cause_sortie_stock`";
            ResultSet res = stmt.executeQuery(requete);
            while(res.next())
            {
                liste.add(new CauseSortieStock(res.getInt("id_cause_sortie_stock"), res.getString("nom_cause")));
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return liste;
    }
}
