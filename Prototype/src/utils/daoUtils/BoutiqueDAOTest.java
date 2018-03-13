package utils.daoUtils;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import model.Boutique;
import org.junit.Test;
import utils.Constants;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class BoutiqueDAOTest {
    private BoutiqueDAO boutiqueDAO = new BoutiqueDAO(Constants.conServ);

    @Test
    public void create() {
        int nb_boutiques_avant = boutiqueDAO.getNbBoutiques();
        Boutique boutique = new Boutique(2,"Carrefour", 3,3,null);
        boutiqueDAO.create(boutique);
        int nb_boutiques_apres = boutiqueDAO.getNbBoutiques();
        assertEquals(nb_boutiques_avant + 1, nb_boutiques_apres);
    }

   // @Test
    public void update() {
    }

    @Test
    public void find() {
        Boutique boutique = new Boutique(1,"AppleStore",1,0,null );
        Boutique boutique2 = boutiqueDAO.find(1);
        assertEquals(boutique.getNom(),boutique2.getNom());
    }

   // @Test
    public void findFromReference() {
    }
}