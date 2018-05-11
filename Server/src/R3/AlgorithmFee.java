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
 * Class which represents the algorithm to calculate and assign a fee to a store
 */
public class AlgorithmFee {
    /**
     * The list of the stores which have to pay
     */
    private ArrayList<Boutique> listeBoutique;
    private BoutiqueDAO bDAO;
    private RedevanceDAO rDAO;

    /**
     * Logger
     */
    Logger logger;

    public AlgorithmFee(BoutiqueDAO bDAO,RedevanceDAO rDAO) {
        this.bDAO = bDAO;
        this.rDAO = rDAO;
        logger = Logger.getLogger(Algorithm.class);
    }


    /**
     * Method to calculate and assign a fee to all the stores if they already have not one
     * */
    public void assignFeeToStore() {

        listeBoutique=this.bDAO.findWhoPay();
        for (int i=0; i<listeBoutique.size(); i++) {
            Boutique b = listeBoutique.get(i);
            System.out.println (this.rDAO.findFeeAssigned(b.getId()));
        if (this.rDAO.findFeeAssigned(b.getId())){

                float red = 0;
                float s = b.getEmplacement().getSuperficie();
                float c = 0;
                float  f = b.getFreq();
                switch (b.getEmplacement().getCat()) {
                    case "*":
                        c = 65;
                        break;
                    case "A":
                        c = 57.5F;
                        break;
                    case "B":
                        c = 50;
                        break;
                    case "C":
                        c = 42.5F;
                        break;
                    case "D":
                        c = 40;
                        break;
                    default:
                        c = 40;
                }

                Calendar calendar = Calendar.getInstance();


                Date auj = calendar.getTime();
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-01");



                red = (s * c) * (1f - s / 70000f + f/s);

                Redevance r = new Redevance(0, b, auj, red);

                this.rDAO.create(r);

            }else{ System.out.println("deja assigne");}



        }
    }


}
