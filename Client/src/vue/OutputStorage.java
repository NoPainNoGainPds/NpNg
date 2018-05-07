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

    private TextLabel<JTextField> nomProduit;
    private JTextField nomProduitTextField;

    private TextLabel<JTextField> idProduit;
    private JTextField idProduitTextField;

    //private TextLabel<JComboBox<Cause>>  cause;
    //private JComboBox<Cause> causeBox;

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
