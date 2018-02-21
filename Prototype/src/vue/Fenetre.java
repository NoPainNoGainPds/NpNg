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
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fenetre extends JFrame implements Runnable{
    private ArrayList<Boutique> listBoutique;
    private JList<Boutique> list;
    private JLabel msgError;
    private InsertBoutique vueInsert;
    public Fenetre(String s)
    {
        super(s);
        this.setSize(Constants.WIDTH*3,Constants.HEIGHT*2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ProduitDAO pDAO = new ProduitDAO();
        BoutiqueDAO bDAO = new BoutiqueDAO();
        this.listBoutique = bDAO.findFromReference();
        System.out.println(listBoutique.size());
        this.msgError = new JLabel("");
        this.add(this.msgError,BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        this.list = new JList<>(listBoutique.toArray(new Boutique[listBoutique.size()]));

        JScrollPane scrollPane = new JScrollPane(this.list);
        panel.add(scrollPane);
        //panel.add(list);
        this.add(panel, BorderLayout.CENTER);

        JButton btn1 = new JButton("Modifier boutique");
        JButton btn2 = new JButton("Afficher boutique");
        JButton btn3 = new JButton("Ajouter boutique");
        JButton btn4 = new JButton("Supprimer boutique");
        btn1.addActionListener(event ->
        {
            Boutique b = this.list.getSelectedValue();
            //new UpdateBoutique(b);
            if(b!=null)
            {
                new UpdateWindow<>(b);
                this.msgError.setText("");
            }else
            {
                this.msgError.setText("No boutique selected");
                this.msgError.setForeground(Color.RED);
            }
        });
        btn2.addActionListener(event ->
        {
            //System.out.println("afficher");
            Boutique b = this.list.getSelectedValue();
            //new UpdateBoutique(b);
            if(b!=null)
            {
                new VueBoutique(b);
                this.msgError.setText("");
            }else
            {
                this.msgError.setText("No boutique selected");
                this.msgError.setForeground(Color.RED);
            }
        });
        btn3.addActionListener(event ->
        {
            Boutique b = new Boutique();
            this.vueInsert = new InsertBoutique(b,this);
            javax.swing.SwingUtilities.invokeLater(this.vueInsert);
        });
        btn4.addActionListener(event ->
        {
            Boutique b = this.list.getSelectedValue();
            //new UpdateBoutique(b);
            //test
            if(b!=null)
            {
                if(!bDAO.delete(b))
                {
                    this.msgError.setText("error");
                    this.msgError.setForeground(Color.RED);
                }else
                {
                    this.msgError.setText("");
                    this.listBoutique.remove(b);
                    this.list.setListData(this.listBoutique.toArray(new Boutique[listBoutique.size()]));
                    this.repaint();
                }
            }else
            {
                this.msgError.setText("No boutique selected");
                this.msgError.setForeground(Color.RED);
            }
        });
        JPanel panel2 = new JPanel();
        panel2.add(btn1);
        panel2.add(btn2);
        panel2.add(btn3);
        panel2.add(btn4);
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
    public void notifyBoutique()
    {
        Boutique b = this.vueInsert.getBoutique();
        if(b!=null)
        {
            this.listBoutique.add(b);
            this.list.setListData(this.listBoutique.toArray(new Boutique[listBoutique.size()]));
            this.repaint();
        }
    }
}
