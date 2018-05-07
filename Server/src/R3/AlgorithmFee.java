package R3;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.Database;
import model.Boutique;
import model.ConnectionPool;
import model.Emplacement;
import model.Redevance;
import org.apache.log4j.Logger;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.EmplacementDAO;
import utils.daoUtils.RedevanceDAO;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Class which represents the algorithm to use to assign a location to a store
 */
public class AlgorithmFee {
    private ArrayList<Boutique> listeBoutique;
    private BoutiqueDAO bDAO;
    private RedevanceDAO rDAO;


    Logger logger;

    public AlgorithmFee(BoutiqueDAO bDAO,RedevanceDAO rDAO) {
        this.bDAO = bDAO;
        this.rDAO = rDAO;
        logger = Logger.getLogger(Algorithm.class);
    }


    /**
     * Method to assign a location to a store after having sorted the lists
     */
    public void assignFeeToStore() {

        listeBoutique=this.bDAO.findWhoPay();
        for (int i=0; i<listeBoutique.size(); i++) {
            Boutique b = listeBoutique.get(i);

            float red=0;
            int s=b.getEmplacement().getSuperficie();
            float c=0;
            switch (b.getEmplacement().getCat()){
                case "*": c=70; break;
                case "A": c=62.5F; break;
                case "B": c=55; break;
                case "C": c=47.5F; break;
                case "D": c=40; break;
                default: c=40;
            }

            Calendar calendar = Calendar.getInstance();


            Date auj=calendar.getTime();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-01");

            System.out.println(formater.format(auj));

            red=(s*c)*(1-s/10000);
            Redevance r=new Redevance(0,b,auj,red);

            this.rDAO.create(r);
            System.out.println("ok");

        }
    }

    public ArrayList<Boutique> findWhoPay() {

        Database db = new Database();


        try {
        Connection connection = db.getConnection();
        Statement stmt = connection.createStatement();
        String requete =  "select b.id_boutique, e.nom_emplacement, e.id_emplacement, e.superficie, c.nom_categorie_emplacement from boutique b, emplacement e, categorie_emplacement c where b.id_emplacement=e.id_emplacement and e.id_categorie_emplacement=c.id_categorie_emplacement and b.id_emplacement != -1";

        ResultSet res = stmt.executeQuery(requete);
            ArrayList list = new ArrayList();
        while (res.next()) {
            list.add(new Boutique (res.getInt("id_boutique"), new Emplacement(res.getString("nom_emplacement"), res.getInt("id_emplacement"), res.getInt("superficie"), res.getString("nom_categorie_emplacement"))));

        }
        res.close();
        stmt.close();
        connection.close();
        logger.info(requete);
    } catch (SQLException e) {
        logger.error(e.toString());
    }
        return null;
}

}
