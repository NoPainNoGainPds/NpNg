//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package vue;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

import javafx.scene.control.RadioButton;
import model.Boutique;
import model.Produit;
import model.StockEntree;
import model.StockSortie;
import net.miginfocom.swing.MigLayout;
import utils.MyListModel;

import utils.daoUtils.StockEntreeDAO;
import utils.daoUtils.StockSortieDAO;

public class    DetailProduct extends JFrame implements Runnable {
    private Boutique store;
    private Image img;
    private ImageComponent logo;
    private JLabel nomProduit;
    private JLabel idProduit;
    private JLabel poids;
    private JLabel longueur;
    private JLabel largeur;
    private JLabel categorie;
    private JLabel msgError;
    ArrayList<String> list=new ArrayList<>();
    JList<String> jlist=new JList<>();
    JScrollPane j=new JScrollPane(jlist);

    public DetailProduct(Boutique store, Produit product) {
        super("Detail Product");
        this.setSize(new Dimension(500, 420));
        this.store = store;
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        URL url = null;
        BufferedImage c = null;
        this.img = Toolkit.getDefaultToolkit().getImage(this.store.getLogo());
        this.logo = new ImageComponent(true);
        this.logo.setImage(this.img);
        panel.add(this.logo, "cell 0 0 5 2, split 4");
        this.nomProduit = new JLabel("Nom du produit: " + product.getNom());
        panel.add(this.nomProduit);

        JRadioButton rb1 = new JRadioButton("Achats");
        JRadioButton rb2 = new JRadioButton("Ventes");
        panel.add(rb1);
        panel.add(rb2);
        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        rb1.addActionListener(new Afficher(store, product, j, panel));
        rb2.addActionListener(new Afficher(store, product, j, panel));
        panel.add(j,"cell 2 2 2 4");
        /*rb1.addActionListener((event) -> {
            StockEntreeDAO entree = new StockEntreeDAO();
            ArrayList<StockEntree> listentree = entree.findFromReference(product.getId(), store.getId());
            MyListModel<StockEntree> listModel = new MyListModel(listentree);
            JList<StockEntree> jlist = new JList(listModel);
            jlist.setFixedCellWidth(90);
            JScrollPane jscrollPane = new JScrollPane(jlist);
            panel.add(jscrollPane, "cell 2 2 2 4");
            panel.repaint();
            panel.revalidate();
        });
        rb2.addActionListener((event) -> {
            StockSortieDAO sortie = new StockSortieDAO();
            ArrayList<StockSortie> listsortie = sortie.findFromReference(product.getId(), store.getId());
            MyListModel<StockSortie> listModel = new MyListModel(listsortie);
            JList<StockSortie> jlist = new JList(listModel);
            jlist.setFixedCellWidth(90);
            JScrollPane jscrollPane = new JScrollPane(jlist);
            panel.add(jscrollPane, "cell 2 2 2 4");
            panel.repaint();
            panel.revalidate();
        });*/
        this.poids = new JLabel("Poids: " + String.valueOf(product.getPoids()));
        panel.add(this.poids, "cell 0 2");
        this.longueur = new JLabel("Longueur: " + String.valueOf(product.getLongueur()));
        panel.add(this.longueur, "cell 0 3");
        this.largeur = new JLabel("Largeur: " + String.valueOf(product.getLargeur()));
        panel.add(this.largeur, "cell 0 4");
        this.categorie = new JLabel("Categorie: " + String.valueOf(product.getQuantite()));
        panel.add(this.categorie, "cell 0 5");
        this.add(panel);
    }



    public class Afficher extends JPanel implements ActionListener{
        Boutique store;
        Produit product;
        JScrollPane j;
        JPanel p;

        public Afficher(Boutique b, Produit produit,JScrollPane j, JPanel p){
            this.store=b;
            this.product=produit;
            this.j=j;
            this.p=p;
        }
        public void actionPerformed(ActionEvent e) {
             try {

               if (e.getActionCommand()=="Achats") {
                   StockEntreeDAO entree = new StockEntreeDAO();
                   ArrayList<StockEntree> list1=new ArrayList<>();
                   list1 = entree.findFromReference(product.getId(), store.getId());
                   MyListModel<StockEntree> listModel1 = new MyListModel(list1);
                   JList<StockEntree> jlist1 = new JList(listModel1);
                   jlist1.setFixedCellWidth(90);

                   jlist1.setListData(list1.toArray(new StockEntree[list1.size()]));
                   j.setViewportView(jlist1);

               } else if(e.getActionCommand()=="Ventes"){
                    StockSortieDAO sortie = new StockSortieDAO();
                    ArrayList<StockSortie> list2=new ArrayList<>();
                    list2 = sortie.findFromReference(product.getId(), store.getId());
                    MyListModel<StockSortie> listModel2 = new MyListModel(list2);
                    JList<StockSortie> jlist2 = new JList(listModel2);
                    jlist2.setFixedCellWidth(90);

                    jlist2.setListData(list2.toArray(new StockSortie[list2.size()]));
                    j.setViewportView(jlist2);
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            p.repaint();
            p.revalidate();


        }

    }


    public void run() {
        this.setVisible(true);
    }
}

