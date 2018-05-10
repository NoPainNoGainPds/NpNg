package utils.daoUtils;

import controller.Client;
import model.Produit;
import model.StockEntree;
import model.StockSortie;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StockEntreeDAO extends DAO<StockEntree> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(StockEntreeDAO.class);
    public StockEntreeDAO(Connection con)
    {
        super(con);
    }

    @Override
    public boolean create(StockEntree obj) {
        System.out.println("creation entree stock");
        return false;
    }

    @Override
    public boolean delete(StockEntree obj) {

        return false;
    }

    @Override
    public boolean update(StockEntree obj) {

        return false;
    }

    @Override
    public StockEntree find(int id) {

        return null;
    }

    @Override
    public ArrayList<StockEntree> findFromReference(int id) {

        return null;
    }
    @Override
    public ArrayList<StockEntree> findFromReference() {

        return null;
    }

    public ArrayList<StockEntree> findFromReference(int id_produit, int id_boutique) {
        try {
            Statement stmt = this.connection.createStatement();
            String requete =  "Select es.id_entree, es.id_boutique, es.date_entree, es.id_produit, p.nom_produit, es.quantite, es.montant FROM entree_stock es, produit p where es.id_produit=p.id_produit and es.id_produit="+id_produit+" and id_boutique=+"+id_boutique+" ORDER BY es.date_entree ASC";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList listEntree = new ArrayList();

            while(res.next()) {
                listEntree.add(new StockEntree(res.getInt("id_entree"),  res.getInt("id_boutique"), new Produit(res.getString("nom_produit"), res.getInt("id_produit")), res.getInt("quantite"), res.getInt("montant"), res.getDate("date_entree")));

            }

            this.logger.info(requete);
            return listEntree;
        } catch (SQLException e) {
            e.printStackTrace();
            this.logger.error("SQLException");
            return null;
        }
    }
}
