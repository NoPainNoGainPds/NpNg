package vue;

import javax.swing.*;

import model.Boutique;
import model.Emplacement;
import model.Produit;
import utils.Constants;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.ProduitDAO;

import java.util.ArrayList;

public class Fenetre extends JFrame implements Runnable{
    public Fenetre(String s)
    {
        super(s);
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ProduitDAO pDAO = new ProduitDAO();
        BoutiqueDAO bDAO = new BoutiqueDAO();
        ArrayList<Boutique> listBoutique = bDAO.findFromReference(0);
        for(Boutique b : listBoutique)
        {
            b.setListeProduit(pDAO.findFromReference(b.getId()));
        }
        //affichage
        for(Boutique b : listBoutique)
        {
            //System.out.println(b);
            for(Produit p : b.getListeProduit())
            {
                //System.out.println(p);
            }
        }
        new UpdateWindow<Boutique>(listBoutique.get(2));
    }
    @Override
    public void run() {
        this.setVisible(true);
    }
}
