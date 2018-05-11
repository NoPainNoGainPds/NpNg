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

/**
 * Class which represents a fee. It contains the methods which access the database.
 */
public class RedevanceDAO extends DAO<Redevance> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(RedevanceDAO.class);

    /**
     * Constructor.
     */
    public RedevanceDAO(Connection con)
    {
        super(con);
    }


    /**
     * Add a new fee in the database.
     * @param obj The fee to add.
     * @return true if it works, false else.
     */
    @Override
    public boolean create(Redevance obj) {
        try
        {
            System.out.println(obj.getMontant_redevance());
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

    /**
     * Delete a fee from the database.
     * @param obj The fee to delete.
     * @return true if it works, false else.
     */
    @Override
    public boolean delete(Redevance obj) {
        try
        {
            String requete = "delete from redevance where id_redevance="+obj.getid_Redevance()+";";
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

    /**
     * Update a fee from the database.
     * @param obj The fee to modify.
     * @return true if it works, false else.
     */
    @Override
    public boolean update(Redevance obj) {

        return false;
    }

    /**
     * Find a fee in the database.
     * @param id The fee's id.
     * @return The fee found.
     */
    @Override
    public Redevance find(int id) {

        return null;
    }

    /**
     * Get all the fees from the database.
     * @return A list of the fees.
     */
    public ArrayList<Redevance> findFromReference() {
        try {
            Statement stmt = this.connection.createStatement();
            String requete =  "Select r.id_redevance, r.id_boutique, b.nom_boutique, ROUND(r.montant_redevance,2), r.date_redevance, b.url_logo, b.id_categorie_boutique, b.id_emplacement from redevance r, boutique b where r.id_boutique=b.id_boutique and  MONTH(r.date_redevance)=MONTH(CURRENT_DATE) and YEAR(r.date_redevance)=YEAR(CURRENT_DATE) order by b.nom_boutique;\n";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList listRedevance = new ArrayList();

            while(res.next()) {
                listRedevance.add(new Redevance(res.getInt("id_redevance"), new Boutique(res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),new Emplacement(res.getString("nom_emplacement"),res.getInt("id_emplacement"),res.getInt("superficie"),res.getString("nom_categorie_emplacement")), res.getString("url_logo")),res.getDate("date_redevance"), res.getFloat("ROUND(r.montant_redevance,2)")));

            }

            this.logger.info(requete);
            return listRedevance;
        } catch (SQLException e) {
            e.printStackTrace();
            this.logger.error("SQLException");
            return null;
        }
    }

    /**
     * Get all the fees of the chosen month and year from the database.
     * @param annee the year
     * @param mois the month
     * @return A list of the fees.
     */
    public ArrayList<Redevance> findFromReference(int annee, int mois) {
        try {
            Statement stmt = this.connection.createStatement();
            String requete =  "Select r.id_redevance, r.id_boutique, b.nom_boutique, ROUND(r.montant_redevance,2), r.date_redevance, b.url_logo, b.id_categorie_boutique, b.id_emplacement, e.nom_emplacement, e.superficie, c.nom_categorie_emplacement, p.nb_client from redevance r, boutique b, emplacement e, categorie_emplacement c, performance p where r.id_boutique=b.id_boutique and b.id_emplacement=e.id_emplacement and e.id_categorie_emplacement=c.id_categorie_emplacement and p.id_boutique=b.id_boutique and MONTH(r.date_redevance)="+mois+"+1 and YEAR(r.date_redevance)="+annee+" and MONTH(p.date_performance)=MONTH(r.date_redevance) and YEAR(p.date_performance)=YEAR(r.date_redevance) order by b.nom_boutique;";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList listRedevance = new ArrayList();

            while(res.next()) {
                listRedevance.add(new Redevance(res.getInt("id_redevance"), new Boutique(res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),new Emplacement(res.getString("nom_emplacement"),res.getInt("id_emplacement"),res.getInt("superficie"),res.getString("nom_categorie_emplacement")), res.getString("url_logo")), res.getDate("date_redevance"), res.getFloat("ROUND(r.montant_redevance,2)"), res.getInt("nb_client")));

            }
            this.logger.info(requete);
            return listRedevance;
        } catch (SQLException e) {
            e.printStackTrace();
            this.logger.error("SQLException");
            return null;
        }
    }

    /**
     * Get the fee by its id from the database.
     * @param id_redevance the id of the fee
     * @return A list of one fee.
     */
    public ArrayList<Redevance> findFromReference(int id_redevance) {
        try {
            Statement stmt = this.connection.createStatement();
            String requete =  "Select r.id_redevance, r.id_boutique, b.nom_boutique, ROUND(r.montant_redevance,2), r.date_redevance, b.url_logo, b.id_categorie_boutique, b.id_emplacement, e.nom_emplacement, e.superficie, c.nom_categorie_emplacement, p.nb_client from redevance r, boutique b, emplacement e, categorie_emplacement c, performance p where r.id_boutique=b.id_boutique and b.id_emplacement=e.id_emplacement and e.id_categorie_emplacement=c.id_categorie_emplacement and p.id_boutique=b.id_boutique and MONTH(p.date_performance)=MONTH(r.date_redevance) and YEAR(p.date_performance)=YEAR(r.date_redevance) and r.id_redevance="+id_redevance+"";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList listRedevance = new ArrayList();

            while(res.next()) {
                listRedevance.add(new Redevance(res.getInt("id_redevance"), new Boutique(res.getInt("id_boutique"),res.getString("nom_boutique"),res.getInt("id_categorie_boutique"),new Emplacement(res.getString("nom_emplacement"),res.getInt("id_emplacement"),res.getInt("superficie"),res.getString("nom_categorie_emplacement")), res.getString("url_logo")), res.getDate("date_redevance"), res.getFloat("ROUND(r.montant_redevance,2)"), res.getInt("nb_client")));

            }
            this.logger.info(requete);
            return listRedevance;
        } catch (SQLException e) {
            e.printStackTrace();
            this.logger.error("SQLException");
            return null;
        }
    }

    /**
     * Get the fee of t of a store id from the database.
     * @param id_boutique the id of the store
     * @return A list of the fees.
     */
    public boolean findFeeAssigned(int id_boutique) {
        try {
            Statement stmt = this.connection.createStatement();
            String requete =  "select id_redevance from redevance where MONTH(date_redevance)=MONTH(NOW()) and YEAR(date_redevance)=YEAR(NOW()) and id_boutique="+id_boutique+"";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList listRedevance = new ArrayList();
            while(res.next()) {
                listRedevance.add(res.getInt("id_redevance"));

            }

            this.logger.info(requete);
            if (listRedevance.isEmpty()){
                return true;
            }else{return false;}


        } catch (SQLException e) {
            e.printStackTrace();
            this.logger.error("SQLException");
            return false;
        }
    }


}
