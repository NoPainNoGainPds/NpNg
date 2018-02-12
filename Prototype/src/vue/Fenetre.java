package vue;

import javax.swing.*;
import javax.swing.border.Border;

import model.Boutique;
import model.Emplacement;
import model.Produit;
import model.StockSortie;
import utils.Constants;
import utils.MyListModel;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.ProduitDAO;

import java.awt.*;
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
        System.out.println(listBoutique.size());
        MyListModel<Boutique> modelList = new MyListModel<>(listBoutique);
        JPanel panel = new JPanel();
        JList list = new JList(modelList);

        JScrollPane scrollPane = new JScrollPane(list);
        panel.add(scrollPane);
        //panel.add(list);
        this.add(panel, BorderLayout.CENTER);

        JButton btn1 = new JButton("update");
        JButton btn2 = new JButton("Afficher");
        btn1.addActionListener(event ->
        {
            Boutique b = (Boutique)list.getSelectedValue();
            new UpdateWindow<Boutique>(b);
        });
        btn2.addActionListener(event ->
        {
            System.out.println("afficher");
        });
        JPanel panel2 = new JPanel();
        panel2.add(btn1);
        panel2.add(btn2);
        this.add(panel2,BorderLayout.EAST);
        /*for(Boutique b : listBoutique)
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
        }*/
        new UpdateWindow<StockSortie>(new StockSortie());
    }
    @Override
    public void run() {
        this.setVisible(true);
    }
}
