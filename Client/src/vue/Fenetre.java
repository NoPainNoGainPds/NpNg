package vue;

import javax.swing.*;

import controller.MapController;
import model.Boutique;
import utils.Constants;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.ProduitDAO;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the frame
 */
public class Fenetre extends JFrame implements Runnable{
    /**
     * list of the stores
     */
    private ArrayList<Boutique> listBoutique;
    /**
     * Jlist of the stores
     */
    private JList<Boutique> list;
    /**
     * Error if needed
     */
    private JLabel msgError;
    /**
     * The map
     */
    private Map map ;
    /**
     * The menu bar
     */
    private JMenuBar mb;
    /**
     * The search button
     */
    private JButton searchButton;
    /**
     * Textfield for searching
     */
    private JTextField searchTextField;

    /**
     * Constructor
     * @param s Name of the frame
     */
    public Fenetre(String s)
    {
        super(s);

        this.setSize(Constants.WIDTH*3,Constants.HEIGHT*2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Layout gridbagconstraint
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        //JTabedPane
        JTabbedPane jtp = new JTabbedPane(JTabbedPane.LEFT);
        //-----------
        //gbc for menu
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 6.0;
        gbc.weighty = 0.0;
        gbc.gridwidth = 6;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        //pour la bar de menu
        this.mb = new JMenuBar();
        populate_menu();
        this.add(this.mb,gbc);
        //use of DAO
        ProduitDAO pDAO = new ProduitDAO(Constants.conServ);
        BoutiqueDAO bDAO = new BoutiqueDAO(Constants.conServ);
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
        //ajout tabbedpane
        jtp.addTab("MAP",null,this.map,"Show map of Stores");
        jtp.addTab("Profils",null,new ManageProfil(),"Show all profils and manage");
        this.add(jtp,gbc);
        //this.add(this.map,gbc);
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

        //ajout de la vue des profils

    }

    /**
     * Run method
     */
    @Override
    public void run() {
        this.setVisible(true);
        this.map.setNews();
        this.map.refresh();
    }

    /**
     * To refresh the frame
     */
    private void refresh()
    {
        BoutiqueDAO bDAO = new BoutiqueDAO(Constants.conServ);
        this.listBoutique = bDAO.findFromReference();

        ArrayList<MyPolygon> liste = new ArrayList<>();
        for(Boutique b: this.listBoutique)
        {
            liste.add(b.getPolygonsView());
        }
        this.map.setPolygons(liste);
        this.revalidate();
        this.map.setNews();
        this.map.refresh();
        this.map.sendData();

    }

    /**
     * Method for populating JMenu
     */
    public void populate_menu()
    {
        JMenu filemenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Save as  PDF");
        JMenuItem save = new JMenuItem("Export as JSon");
        filemenu.add(open);
        filemenu.add(save);

        this.mb.add(filemenu);

        JMenu editmenu = new JMenu("Edit");
        JMenuItem refresh = new JMenuItem("Refresh");
        refresh.addActionListener(event ->
        {
            this.refresh();
            this.repaint();
            System.out.println("refresh map");
        });
        editmenu.add(refresh);
        this.mb.add(editmenu);


        JMenu viewmenu = new JMenu("More");

        JMenuItem piano = new JMenuItem("Settings");
        viewmenu.add(piano);
        JMenuItem rythme = new JMenuItem("About");
        JMenuItem accueil = new JMenuItem("Accueil");
        viewmenu.add(accueil);
        viewmenu.add(rythme);
        viewmenu.addSeparator();
        this.mb.add(viewmenu);

        this.searchTextField = new JTextField("",45);
        this.searchTextField.setBorder(new TextBubbleBorder(Color.GRAY.darker(),2,4,0));
        this.searchTextField.addActionListener(event ->
                this.notifyBoutique());
        this.mb.add(this.searchTextField);
        this.searchButton = new JButton(new ImageIcon("Client/src/res/search-icon-large.png"));
        this.searchButton.setBorder(new TextBubbleBorder(Color.GRAY.darker(),2,4,0));
        this.searchButton.addActionListener(event ->
            this.notifyBoutique());
        this.mb.add(this.searchButton);
    }

    /**
     * Method which search in database where a product can be find and show it on the map
     */
    public void notifyBoutique()
    {
        BoutiqueDAO boutiqueDAO = new BoutiqueDAO(Constants.conServ);
        Integer[] list = boutiqueDAO.findWhoSale(this.searchTextField.getText());
        this.map.setSearch(list);
    }
}
