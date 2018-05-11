package utils.daoUtils;


import model.BonLivraison;
import org.apache.log4j.Logger;
import sun.nio.ch.sctp.SctpStdSocketOption;
import utils.DAO;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BonLivraisonDAO extends DAO<BonLivraison>{

    private Logger logger = Logger.getLogger(BonLivraisonDAO.class);
    public BonLivraisonDAO(Connection con)
    {
        super(con);
    }

    @Override
    public boolean create(BonLivraison obj) {
        try {
            System.out.println("Create BL");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(obj.getDate_livraison());

            String requete = "INSERT INTO bon_livraison (id_bonlivraison, date_livraison, id_fournisseur) VALUES ('" +obj.getId_bonlivraison()+"','"+ date + "', '" + obj.getId_fournisseur() + "');";
            Statement stmt = this.connection.createStatement();
            stmt.executeUpdate(requete);
            logger.info(requete);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e.toString());
            return false;
        }
    }

    @Override
    public boolean delete(BonLivraison obj) {
        return false;
    }

    @Override
    public boolean update(BonLivraison obj) {
        return false;
    }

    @Override
    public BonLivraison find(int id) {
        return null;
    }

    @Override
    public ArrayList<BonLivraison> findFromReference() {
        return null;
    }

    @Override
    public ArrayList<BonLivraison> findFromReference(int id) {
        return null;
    }

}
