package utils.daoUtils;

import controller.Client;
import model.Produit;
import model.Redevance;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RedevanceDAO extends DAO<Redevance> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(StockEntreeDAO.class);
    public RedevanceDAO(Connection con)
    {
        super(con);
    }

    @Override
    public boolean create(Redevance obj) {

        return false;
    }

    @Override
    public boolean delete(Redevance obj) {

        return false;
    }

    @Override
    public boolean update(Redevance obj) {

        return false;
    }

    @Override
    public Redevance find(int id) {

        return null;
    }

    @Override
    public ArrayList<Redevance> findFromReference(int id) {

        return null;
    }

    public ArrayList<Redevance> findFromReference() {
        try {
            Statement stmt = this.connection.createStatement();
            String requete =  "Select r.id_redevance, r.id_boutique, b.nom_boutique, r.montant_redevance, r.date_redevance from redevance r, boutique b where r.id_boutique=b.id_boutique and  MONTH(r.date_redevance)=MONTH(CURRENT_DATE) and YEAR(r.date_redevance)=YEAR(CURRENT_DATE) order by b.nom_boutique";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList listRedevance = new ArrayList();

            while(res.next()) {
                listRedevance.add(new Redevance(res.getInt("id_redevance"),  res.getInt("id_boutique"), res.getDate("date_redevance"), res.getInt("montant_redevance")));

            }

            this.logger.info(requete);
            return listRedevance;
        } catch (SQLException e) {
            e.printStackTrace();
            this.logger.error("SQLException");
            return null;
        }
    }

    public ArrayList<Redevance> findFromReference(int annee, int mois) {
        try {
            Statement stmt = this.connection.createStatement();
            String requete =  "Select r.id_redevance, r.id_boutique, b.nom_boutique, r.montant_redevance, r.date_redevance from redevance r, boutique b where r.id_boutique=b.id_boutique and  MONTH(r.date_redevance)="+mois+"+1 and YEAR(r.date_redevance)="+annee+" order by b.nom_boutique";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList listRedevance = new ArrayList();

            while(res.next()) {
                listRedevance.add(new Redevance(res.getInt("id_redevance"),  res.getInt("id_boutique"), res.getDate("date_redevance"), res.getInt("montant_redevance")));

            }

            this.logger.info(requete);
            return listRedevance;
        } catch (SQLException e) {
            e.printStackTrace();
            this.logger.error("SQLException");
            return null;
        }
    }
}
