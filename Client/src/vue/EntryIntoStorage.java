package vue;

import com.toedter.calendar.JTextFieldDateEditor;
import model.Boutique;
import model.StockEntree;
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
    private TextLabel <JComboBox<Boutique>> nomDeLaBoutique;
    private JComboBox<Boutique> nomDeLaBoutiqueBox;
    /**
     * Textfield to enter the date
     */
    private TextLabel <JTextField> dateEntreeStock;
    private JTextField dateEntreeStockTextField;
    /**
     * Textfield to enter the quantity
     */
    private TextLabel <JTextField> quantite;
    private JTextField quantiteTextField;
    /**
     * Textfield to enter the amount
     */
    private TextLabel <JTextField> montant;
    private JTextField montantTextField;
    /**
     * Textfield to enter the provider
     */
    private TextLabel <JTextField> fournisseur;
    private JTextField fournisseurTextField;
    /**
     * Textfield to enter unitary cost
     */
    private TextLabel <JTextField> coutUnitaire;
    private JTextField coutUnitaireTextField;

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

            nomDeLaBoutiqueBox = new JComboBox<Boutique>();
            nomDeLaBoutiqueBox.setPreferredSize(new Dimension(250,50));
            nomDeLaBoutique = new TextLabel (nomDeLaBoutiqueBox, new JLabel("Nom de la boutique"));
            this.add(nomDeLaBoutique, "cell 0 2 1 1");

            dateEntreeStockTextField = new JTextField(20);
            dateEntreeStockTextField.setPreferredSize(new Dimension(300,50));
            dateEntreeStock = new TextLabel(dateEntreeStockTextField, new JLabel("Date entree du stock"));
            this.add(dateEntreeStock, "cell 2 2 1 1");

            quantiteTextField = new JTextField(20);
            quantiteTextField.setPreferredSize(new Dimension(300,50));
            quantite = new TextLabel(quantiteTextField, new JLabel("Quantite"));
            this.add(quantite, "cell 4 2 1 1");

            montantTextField = new JTextField(20);
            montantTextField.setPreferredSize(new Dimension(300,50));
            montant = new TextLabel(montantTextField, new JLabel("Montant"));
            this.add(montant, "cell 0 4 1 1");

            /*fournisseurTextField = new JTextField(20);
            fournisseurTextField.setPreferredSize(new Dimension(300,50));
            fournisseur = new TextLabel(new JTextField(20), new JLabel("Fournisseur"));
            this.add(fournisseur, "cell 2 4 1 1");*/

            /*coutUnitaireTextField = new JTextField(20);
            coutUnitaireTextField.setPreferredSize(new Dimension(300,50));
            coutUnitaire = new TextLabel(new JTextField(20), new JLabel("Cout Unitaire"));
            this.add(coutUnitaire, "cell 4 4 1 1");*/


            validerBoutton = new JButton("Valider");

            JPanel Buttonpan = new JPanel(new MigLayout("", "[center, grow]"));
            Buttonpan.add(validerBoutton);
            this.add(Buttonpan, "dock south");
        }

        private void controler(){
            this.validerBoutton.addActionListener(event ->{
                StockEntree stToSend = new StockEntree();

                //stToSend.setId_boutique();) probleme demande Id et pas nom
                stToSend.setQuantite(Integer.parseInt(this.quantiteTextField.getText()));
                //stToSend.setDate(); comment on fait les dates ?
                stToSend.setMontant(Integer.parseInt(this.montantTextField.getText()));

            });
        }
    }
