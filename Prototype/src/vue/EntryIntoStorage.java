package vue;

import com.toedter.calendar.JTextFieldDateEditor;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Represents the view which include the entry to a storage
 */
public class EntryIntoStorage extends JPanel{
    /**
     * Name of the product
     */
    private JLabel nomProduitLabel;
    /**
     * Textfield to enter name of the store
     */
    private TextLabel <JTextField> nomDeLaBoutique;
    /**
     * Textfield to enter the date
     */
    private TextLabel <JTextField> dateEntreeStock;
    /**
     * Textfield to enter the quantity
     */
    private TextLabel <JTextField> quantite;
    /**
     * Textfield to enter the amount
     */
    private TextLabel <JTextField> montant;
    /**
     * Textfield to enter the provider
     */
    private TextLabel <JTextField> fournisseur;
    /**
     * Textfield to enter unitary cost
     */
    private TextLabel <JTextField> coutUnitaire;

    /**
     * product's picture
     */
    private ImageComponent PhotoProduit;
    /**
     * Image
     */
    private Image img;

    /**
     * Valdation button
     */
    private JButton validerBoutton;

    /**
     * Constructor
     */
    public EntryIntoStorage(){
            this.View();
        }

    /**
     * Method to show the view
     */
    public void View(){

            this.setLayout(new MigLayout("inset 0 20 20 20 ", "[fill, grow]", "[fill, grow]"));

            JPanel logopan = new JPanel(new MigLayout("inset 20 0 0 0 ", "[center, grow]"));
            this.add(logopan, "dock north");


            this.img = Toolkit.getDefaultToolkit().getImage("Prototype/src/res/PapierToilette.jpg");
            this.PhotoProduit = new ImageComponent(true);
            this.PhotoProduit.setImage(this.img);
            this.PhotoProduit.setMinimumSize(new Dimension(250 ,250));
            logopan.add(this.PhotoProduit,"cell 0 0 1 1, split 2");

            nomProduitLabel = new JLabel("Nom du produit");
            logopan.add(nomProduitLabel, "cell 1 0 1 1");


            nomDeLaBoutique = new TextLabel (new JTextField(20), new JLabel("Nom de la boutique"));
            this.add(nomDeLaBoutique, "cell 0 2 1 1");

            dateEntreeStock = new TextLabel(new JTextField(20), new JLabel("Date entree du stock"));
            this.add(dateEntreeStock, "cell 2 2 1 1");

            quantite = new TextLabel(new JTextField(20), new JLabel("Quantite"));
            this.add(quantite, "cell 4 2 1 1");


            montant = new TextLabel(new JTextField(20), new JLabel("Montant"));
            this.add(montant, "cell 0 4 1 1");

            fournisseur = new TextLabel(new JTextField(20), new JLabel("Fournisseur"));
            this.add(fournisseur, "cell 2 4 1 1");

            coutUnitaire = new TextLabel(new JTextField(20), new JLabel("Cout Unitaire"));
            this.add(coutUnitaire, "cell 4 4 1 1");


            validerBoutton = new JButton("Valider");

            JPanel Buttonpan = new JPanel(new MigLayout("", "[center, grow]"));
            Buttonpan.add(validerBoutton);
            this.add(Buttonpan, "dock south");


        }
    }
