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
     * Textfield to enter name of the store
     */
    private TextLabel <JComboBox<Boutique>> nomDeLaBoutique;
    private JComboBox<Boutique> nomDeLaBoutiqueBox;

    private TextLabel<JTextField> nomProduit;
    private JTextField nomProduitTextField;

    private TextLabel<JTextField> idProduit;
    private JTextField idProduitTextField;
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

    private TextLabel<JTextField> bonLivraison;
    private JTextField bonLivraisonTextField;
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


            bonLivraisonTextField = new JTextField(20);
            bonLivraisonTextField.setPreferredSize(new Dimension(250, 50));
            bonLivraison = new TextLabel(bonLivraisonTextField, new JLabel ("Numero du bon de livraison"));
            this.add(bonLivraison, "cell 0 2 1 1");

            dateEntreeStockTextField = new JTextField(20);
            dateEntreeStockTextField.setPreferredSize(new Dimension(250,50));
            dateEntreeStock = new TextLabel(dateEntreeStockTextField, new JLabel("Date entree du stock"));
            this.add(dateEntreeStock, "cell 2 2 1 1");

            fournisseurTextField = new JTextField(20);
            fournisseurTextField.setPreferredSize(new Dimension(250,50));
            fournisseur = new TextLabel(new JTextField(20), new JLabel("Fournisseur"));
            this.add(fournisseur, "cell 4 2 1 1");

            nomDeLaBoutiqueBox = new JComboBox<Boutique>();
            nomDeLaBoutiqueBox.setPreferredSize(new Dimension(250, 50));
            nomDeLaBoutique = new TextLabel (nomDeLaBoutiqueBox, new JLabel("Nom de la boutique"));
            this.add(nomDeLaBoutique, "cell 6 2 1 1");




            idProduitTextField = new JTextField(20);
            idProduitTextField.setPreferredSize(new Dimension(250, 50));
            idProduit = new TextLabel(idProduitTextField, new JLabel("id du produit"));
            this.add(idProduit, "cell 0 4 1 1");

            nomProduitTextField = new JTextField(20);
            nomProduitTextField.setPreferredSize(new Dimension(250, 50));
            nomProduit = new TextLabel(nomProduitTextField, new JLabel("nom du produit"));
            this.add(nomProduit, "cell 2 4 1 1");

            quantiteTextField = new JTextField(20);
            quantiteTextField.setPreferredSize(new Dimension(250,50));
            quantite = new TextLabel(quantiteTextField, new JLabel("Quantite"));
            this.add(quantite, "cell 4 4 1 1");

            /*montantTextField = new JTextField(20);
            montantTextField.setPreferredSize(new Dimension(250,50));
            montant = new TextLabel(montantTextField, new JLabel("Montant"));
            this.add(montant, "cell 0 4 1 1");*/

            coutUnitaireTextField = new JTextField(20);
            coutUnitaireTextField.setPreferredSize(new Dimension(250,50));
            coutUnitaire = new TextLabel(coutUnitaireTextField, new JLabel("Cout Unitaire"));
            this.add(coutUnitaire, "cell 6 4 1 1");


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
