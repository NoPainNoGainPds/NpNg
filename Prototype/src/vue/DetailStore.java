package vue;

import model.Boutique;
import model.Produit;
import utils.Constants;
import utils.daoUtils.ProduitDAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DetailStore extends JFrame implements Runnable {
    private Boutique store;
    private ProduitDAO pDao;
    public DetailStore(Boutique store)
    {
        super("Detail Store");
        this.setSize(new Dimension(Constants.HEIGHT,Constants.WIDTH));
        this.store = store;
        this.pDao = new ProduitDAO(Constants.conServ);
        ArrayList<Produit> listeProduct = this.pDao.findFromReference(this.store.getId());
        for(Produit p : listeProduct)
        {
            System.out.println(p);
        }
    }
    public void run()
    {
        this.setVisible(true);
    }
}
