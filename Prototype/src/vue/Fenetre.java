package vue;

import javax.swing.*;

import model.Boutique;
import utils.Constants;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.ProduitDAO;

public class Fenetre extends JFrame implements Runnable{
    public Fenetre(String s)
    {
        super(s);
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ProduitDAO pDAO = new ProduitDAO();
        BoutiqueDAO bDAO = new BoutiqueDAO();
        Boutique b1 = bDAO.find(1);
        System.out.println(b1);
    }
    @Override
    public void run() {
        this.setVisible(true);
    }
}
