package utils.daoUtils;

import controller.Client;
import model.Boutique;
import model.Emplacement;
import model.Produit;
import model.Redevance;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RedevanceDAO extends DAO<Redevance> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(RedevanceDAO.class);
    public RedevanceDAO(Connection con)
    {
        super(con);
    }

    @Override
    public boolean create(Redevance obj) {
        try
        {
            String montant = Float.toString(obj.getMontant_redevance());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(obj.getDate_redevance());
            String id_boutique = Integer.toString(obj.getId_boutique().getId());
            //System.out.println(montant+" "+date+" "+id_boutique);
            String requete = "INSERT INTO redevance (montant_redevance,date_redevance, id_boutique) VALUES ('"+montant+"','"+date+"','"+id_boutique+"');";
            Statement stmt = this.connection.createStatement();
            stmt.executeUpdate(requete);
            logger.info(requete);
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            logger.error(e.toString());
            return false;
        }
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
            String requete =  "Select r.id_redevance, r.id_boutique, b.nom_boutique, r.montant_redevance, r.date_redevance, b.url_logo, b.id_categorie_boutique, b.id_emplacement from redevance r, boutique b where r.id_boutique=b.id_boutique and  MONTH(r.date_redevance)=MONTH(CURRENT_DATE) and YEAR(r.date_redevance)=YEAR(CURRENT_DATE) order by b.nom_boutique;\n";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList listRedevance = new ArrayList();

            while(res.next()) {
                listRedevance.add(new Redevance(res.getInt("id_redevance"), new Boutique(res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),new Emplacement(res.getString("nom_emplacement"),res.getInt("id_emplacement"),res.getInt("superficie"),res.getString("nom_categorie_emplacement")), res.getString("url_logo")),res.getDate("date_redevance"), res.getInt("montant_redevance")));

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
            String requete =  "Select r.id_redevance, r.id_boutique, b.nom_boutique, r.montant_redevance, r.date_redevance, b.url_logo, b.id_categorie_boutique, b.id_emplacement, e.nom_emplacement, e.superficie, c.nom_categorie_emplacement from redevance r, boutique b, emplacement e, categorie_emplacement c where r.id_boutique=b.id_boutique and b.id_emplacement=e.id_emplacement and e.id_categorie_emplacement=c.id_categorie_emplacement and MONTH(r.date_redevance)="+mois+"+1 and YEAR(r.date_redevance)="+annee+" order by b.nom_boutique";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList listRedevance = new ArrayList();

            while(res.next()) {
                listRedevance.add(new Redevance(res.getInt("id_redevance"), new Boutique(res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),new Emplacement(res.getString("nom_emplacement"),res.getInt("id_emplacement"),res.getInt("superficie"),res.getString("nom_categorie_emplacement")), res.getString("url_logo")), res.getDate("date_redevance"), res.getInt("montant_redevance")));

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
