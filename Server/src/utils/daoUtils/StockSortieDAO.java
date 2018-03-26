package utils.daoUtils;

import controller.Client;
import model.Produit;
import model.StockSortie;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StockSortieDAO extends DAO<StockSortie> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(StockSortieDAO.class);
    public StockSortieDAO(Connection con)
    {
        super(con);
    }

    @Override
    public boolean create(StockSortie obj) {
        return false;
    }

    @Override
    public boolean delete(StockSortie obj) {
        return false;
    }

    @Override
    public boolean update(StockSortie obj) {
        return false;
    }

    @Override
    public StockSortie find(int id) {
        return null;
    }

    @Override
    public ArrayList<StockSortie> findFromReference(int id) {
        return null;
    }
    @Override
    public ArrayList<StockSortie> findFromReference() {
        return null;
    }

    public ArrayList<StockSortie> findFromReference(int id_produit, int id_boutique) {
        try {
            Statement stmt = this.connection.createStatement();
            String requete = "Select ss.id_sortie, ss.id_boutique, p.nom_produit, ss.id_produit, ss.date_sortie, ss.quantite FROM sortie_stock ss, produit p where ss.id_produit=p.id_produit and ss.id_produit=" + id_produit + " and id_boutique=" + id_boutique + " ORDER BY ss.date_sortie ASC;";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList listSortie = new ArrayList();

            while(res.next()) {
                listSortie.add(new StockSortie(res.getInt("id_sortie"),  res.getInt("id_boutique"), new Produit(res.getString("nom_produit"), res.getInt("id_produit")), res.getInt("quantite"), res.getDate("date_sortie")));
            }

            this.logger.info(requete);
            return listSortie;
        } catch (SQLException e) {
            e.printStackTrace();
            this.logger.error("SQLException");
            return null;
        }
    }
}
