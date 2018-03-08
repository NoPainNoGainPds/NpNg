package vue;

import javax.swing.*;
import javax.swing.border.Border;

import controller.MapController;
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
    private Map map ;
    private JMenuBar mb;
    public Fenetre(String s)
    {
        super(s);
        this.setSize(Constants.WIDTH*3,Constants.HEIGHT*2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Layout gridbagconstraint
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        //-----------
        //gbc for menu
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 6.0;
        gbc.weighty = 0.0;
        gbc.gridwidth = 6;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        //pour la bar de menu
        this.mb = new JMenuBar();
        populate_menu();
        this.add(this.mb,gbc);
        //use of DAO
        ProduitDAO pDAO = new ProduitDAO();
        BoutiqueDAO bDAO = new BoutiqueDAO();
        this.listBoutique = bDAO.findFromReference();
        //--------------------
        //System.out.println(listBoutique.size());

        //add map
        this.map = new Map();
        ArrayList<MyPolygon> liste = new ArrayList<>();
        for(Boutique b: this.listBoutique)
        {
            liste.add(b.getPolygonsView());
        }
        this.map.setPolygons(liste);
        MapController mapC = new MapController(this.map);
        this.map.addMouseMotionListener(mapC);
        this.map.addMouseListener(mapC);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 5.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 5;
        gbc.gridheight = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        map.setBackground(Color.BLUE);
        this.add(this.map,gbc);
        //this.add(this.map,BorderLayout.CENTER);
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.gridwidth = 1;
        gbc.gridheight = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        RightPanelView rpv = new RightPanelView();
        this.map.setRpv(rpv);
        this.add(rpv,gbc);
        //add button in the frame
        //controller of the btn1
    }
    @Override
    public void run() {
        this.setVisible(true);
        this.map.setNews();
        this.map.refresh();
    }
    public void populate_menu()
    {
        JMenu filemenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Ouvrir");
        JMenuItem save = new JMenuItem("Sauvegarder");
        filemenu.add(open);
        filemenu.add(save);

        this.mb.add(filemenu);

        JMenu editmenu = new JMenu("Edit");
        this.mb.add(editmenu);


        JMenu viewmenu = new JMenu("View");

        JMenuItem piano = new JMenuItem("Ouvrir Piano");
        viewmenu.add(piano);
        JMenuItem rythme = new JMenuItem("Ouvrir rythme");
        JMenuItem accueil = new JMenuItem("Accueil");
        viewmenu.add(accueil);
        viewmenu.add(rythme);
        viewmenu.addSeparator();
        //viewmenu.add(color);
        this.mb.add(viewmenu);
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
