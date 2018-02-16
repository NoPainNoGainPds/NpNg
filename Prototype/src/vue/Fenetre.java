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
        this.setSize(Constants.WIDTH*3,Constants.HEIGHT*2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ProduitDAO pDAO = new ProduitDAO();
        BoutiqueDAO bDAO = new BoutiqueDAO();
        ArrayList<Boutique> listBoutique = bDAO.findFromReference();
        System.out.println(listBoutique.size());
        MyListModel<Boutique> modelList = new MyListModel<>(listBoutique);
        JPanel panel = new JPanel();
        JList<Boutique> list = new JList<>(modelList);

        JScrollPane scrollPane = new JScrollPane(list);
        panel.add(scrollPane);
        //panel.add(list);
        this.add(panel, BorderLayout.CENTER);

        JButton btn1 = new JButton("Modifier boutique");
        JButton btn2 = new JButton("Afficher boutique");
        JButton btn3 = new JButton("Ajouter boutique");
        btn1.addActionListener(event ->
        {
            Boutique b = list.getSelectedValue();
            //new UpdateBoutique(b);
            new UpdateWindow<>(b);
        });
        btn2.addActionListener(event ->
        {
            //System.out.println("afficher");
            Boutique b = list.getSelectedValue();
            new VueBoutique(b);
        });
        btn3.addActionListener(event ->
        {
            Boutique b = new Boutique();
            new InsertBoutique(b);
            modelList.addElement(b);
            this.repaint();
        });
        JPanel panel2 = new JPanel();
        panel2.add(btn1);
        panel2.add(btn2);
        panel2.add(btn3);
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
        //new UpdateWindow<StockSortie>(new StockSortie());
    }
    @Override
    public void run() {
        this.setVisible(true);
    }
}
