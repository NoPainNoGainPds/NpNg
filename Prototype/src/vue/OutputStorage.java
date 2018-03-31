package vue;

import model.Boutique;
import model.StockSortie;
import net.miginfocom.swing.MigLayout;
import utils.Constants;
import utils.daoUtils.StockSortieDAO;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 *
 */
public class OutputStorage extends JPanel{

    private JLabel nomProduitLabel;

    /**
     * Name of the product
     */
    private TextLabel <JComboBox<Boutique>> nomDeLaBoutique;
    private JComboBox<Boutique> nomDeLaBoutiqueBox;

    /**
     * Release Date
     */
    private TextLabel <JTextField> dateSortieStock;
    private JTextField dateSortieStockTextField;

    /**
     * The Quantity
     */
    private TextLabel <JTextField> quantite;
    private JTextField quantiteTextField;

    /**
     * Amount
     */
    private TextLabel <JTextField> montant;
    private JTextField montantTextField;

    /**
     * The Client
     */
    private TextLabel <JTextField> client;
    private JTextField clientTextField;

    /**
     * Cost Per Unit
     */
    private TextLabel <JTextField> coutUnitaire;
    private JTextField coutUnitaireTextField;


    private ImageComponent PhotoProduit;
    private Image img;

    private StockSortieDAO ssDAO;

    private JButton validerBoutton;

    /**
     * Constructor
     */
    public OutputStorage(){
        this.View();
        this.ssDAO = new StockSortieDAO();
    }

    /**
     *
     */
    public void View(){

        this.setLayout(new MigLayout("inset 0 5 5 5", "[fill, grow]", "[fill, grow]"));

        JPanel logopan = new JPanel(new MigLayout("inset 30 0 0 0 ", "[center, grow]"));
        this.add(logopan, "dock north");


        this.img = Toolkit.getDefaultToolkit().getImage("Prototype/src/res/PapierToilette.jpg");
        this.PhotoProduit = new ImageComponent(true);
        this.PhotoProduit.setImage(this.img);
        this.PhotoProduit.setMinimumSize(new Dimension(250 ,250));
        logopan.add(this.PhotoProduit,"cell 0 0 1 1, split 2");

        nomProduitLabel = new JLabel("Nom du produit");
        logopan.add(nomProduitLabel, "cell 1 0 1 1");


        nomDeLaBoutiqueBox = new JComboBox<Boutique>();
        nomDeLaBoutiqueBox.setPreferredSize(new Dimension(300,50));
        nomDeLaBoutique = new TextLabel (nomDeLaBoutiqueBox, new JLabel("Nom de la boutique"));
        this.add(nomDeLaBoutique, "cell 0 2 1 1");

        dateSortieStockTextField = new JTextField(20);
        dateSortieStockTextField.setPreferredSize(new Dimension(300,50));
        dateSortieStock = new TextLabel(dateSortieStockTextField, new JLabel("Date Achat"));
        this.add(dateSortieStock, "cell 2 2 1 1");

        quantiteTextField = new JTextField(20);
        quantiteTextField.setPreferredSize(new Dimension(300,50));
        quantite = new TextLabel(quantiteTextField, new JLabel("Quantite"));
        this.add(quantite, "cell 4 2 1 1");


        /*montantTextField = new JTextField(20);
        montantTextField.setPreferredSize(new Dimension(300,50));
        montant = new TextLabel(montantTextField, new JLabel("Montant"));
        this.add(montant, "cell 0 4 1 1");*/

        /*clientTextField = new JTextField(20);
        clientTextField.setPreferredSize(new Dimension(300,50));
        client = new TextLabel(clientTextField, new JLabel("Fournisseur"));
        this.add(client, "cell 0 4 1 1");*/

        /*coutUnitaireTextField = new JTextField(20);
        coutUnitaireTextField.setPreferredSize(new Dimension(300,50));
        coutUnitaire = new TextLabel(coutUnitaireTextField, new JLabel("Cout Unitaire"));
        this.add(coutUnitaire, "cell 4 4 1 1");*/


        validerBoutton = new JButton("Valider");

        JPanel Buttonpan = new JPanel(new MigLayout("inset 0 0 15 0 ", "[center, grow]"));
        Buttonpan.add(validerBoutton);
        this.add(Buttonpan, "dock south");
    }

    private void controler(){
        this.validerBoutton.addActionListener(event ->
        {
            StockSortie ssToSend = new StockSortie();
            //ssToSend.setDate(...);  Comment on fait la date lol
            //ssToSend.setId_boutique();  Erreur pcq on recupere un nom et pas l'id
            ssToSend.setQuantite(Integer.parseInt(this.quantiteTextField.getText()));


        });
    }
}
