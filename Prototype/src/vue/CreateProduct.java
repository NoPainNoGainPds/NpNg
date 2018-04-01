package vue;

import model.CategorieProduit;
import model.Produit;
import net.miginfocom.swing.MigLayout;
import utils.Constants;
import utils.daoUtils.ProduitDAO;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 *  Represents the view which create product's details
 */

public class CreateProduct extends JPanel{

    /**
     * Name of the product
     */
    private TextLabel <JTextField> nomDuProduit;
    private JTextField nomDuProduitTextField;

    /**
     * Product category's
     */
    private TextLabel <JComboBox<CategorieProduit>> categorie;
    private JComboBox<CategorieProduit> categorieBox;

    /**
     * Barcode
     */
    private TextLabel <JTextField> codeBarre;
    private JTextField codeBarreTextField;

    /**
     * Shop name's
     */
    private TextLabel <JTextField> nomDeLaBoutique;
    private JTextField nomDeLaBoutiqueTextField;

    /**
     * The Quantity
     */
    private TextLabel <JTextField> quantite;
    private JTextField quantiteTextField;

    /**
     * Cost Per Unit
     */
    private TextLabel <JTextField> coutUnitaire;
    private JTextField coutUnitaireTextField;

    /**
     * The Width
     */
    private TextLabel <JTextField> largeur;
    private JTextField largeurTextField;

    /**
     * The Length
     */
    private TextLabel <JTextField> longueur;
    private JTextField longueurTextField;
    /**
     * The Weight
     */
    private TextLabel <JTextField> poids;
    private JTextField poidsTextField;

    private ProduitDAO pDAO;

    /**
     * Submit
     */
    private JButton validerBoutton;

    /**
     * Constructor
     */
    public CreateProduct(){
        this.view();
        this.pDAO = new ProduitDAO(Constants.conServ);
        this.controler();
    }


    private void view (){

        this.setLayout(new MigLayout("inset 30 ", "[fill, grow]", "[fill, grow]"));

        nomDuProduitTextField = new JTextField(20);
        nomDuProduitTextField.setPreferredSize(new Dimension(300,50));
        nomDuProduit = new TextLabel (nomDuProduitTextField, new JLabel("Nom du Produit"));
        this.add(nomDuProduit, "cell 0 1 1 1");

        categorieBox = new JComboBox<CategorieProduit>();
        categorieBox.setPreferredSize(new Dimension(250,50));
        categorie = new TextLabel(categorieBox, new JLabel("Categorie"));
        this.add(categorie, "cell 2 1 1 1");

        codeBarreTextField = new JTextField(20);
        codeBarreTextField.setPreferredSize(new Dimension(300,50));
        codeBarre = new TextLabel(codeBarreTextField, new JLabel("Code Barre"));
        this.add(codeBarre, "cell 4 1 1 1");


        /*nomDeLaBoutiqueTextField = new JTextField(20);
        nomDeLaBoutiqueTextField.setPreferredSize(new Dimension(300,50));
        nomDeLaBoutique = new TextLabel(nomDeLaBoutiqueTextField, new JLabel("Nom de la Boutique"));
        this.add(nomDeLaBoutique, "cell 0 3 1 1");*/

        quantiteTextField = new JTextField(20);
        quantiteTextField.setPreferredSize(new Dimension(300,50));
        quantite = new TextLabel(quantiteTextField, new JLabel("Quantite"));
        this.add(quantite, "cell 0 3 1 1");

        coutUnitaireTextField = new JTextField(20);
        coutUnitaireTextField.setPreferredSize(new Dimension(300,50));
        coutUnitaire = new TextLabel(coutUnitaireTextField, new JLabel("Cout Unitaire"));
        this.add(coutUnitaire, "cell 2 3 1 1");

        largeurTextField = new JTextField(20);
        largeurTextField.setPreferredSize(new Dimension(300,50));
        largeur = new TextLabel(largeurTextField, new JLabel("Largeur"));
        this.add(largeur, "cell 0 5 1 1");

        longueurTextField = new JTextField(20);
        longueurTextField.setPreferredSize(new Dimension(300,50));
        longueur = new TextLabel(longueurTextField, new JLabel("Longueur"));
        this.add(longueur, "cell 2 5 1 1");

        poidsTextField = new JTextField(20);
        poidsTextField.setPreferredSize(new Dimension(300,50));
        poids = new TextLabel(poidsTextField, new JLabel("Poids"));
        this.add(poids, "cell 4 5 1 1");


        validerBoutton = new JButton("Valider");

        JPanel Buttonpan = new JPanel(new MigLayout("", "[center, grow]"));
        Buttonpan.add(validerBoutton);
        this.add(Buttonpan, "dock south");
    }


    private void controler()
    {
        this.validerBoutton.addActionListener(event ->
        {
            int valide = 0;
            //verification des input
            if(this.nomDuProduit.field.getText().equals(""))
            {
                this.nomDuProduit.field.setBackground(Color.RED);
                valide++;
            }
            if(this.largeurTextField.getText().equals(""))
            {
                this.largeurTextField.setBackground(Color.RED);
                valide++;
            }
            if(this.longueurTextField.getText().equals(""))
            {
                this.longueurTextField.setBackground(Color.RED);
                valide++;
            }
            if(this.poidsTextField.getText().equals(""))
            {
                this.poidsTextField.setBackground(Color.RED);
                valide++;
            }
            if(this.coutUnitaireTextField.getText().equals(""))
            {
                this.coutUnitaireTextField.setBackground(Color.RED);
                valide++;
            }
            if(this.quantiteTextField.getText().equals(""))
            {
                this.quantiteTextField.setBackground(Color.RED);
                valide++;
            }
            if(this.codeBarreTextField.getText().equals(""))
            {
                this.codeBarreTextField.setBackground(Color.RED);
                valide++;
            }
            if(valide==0){
            //recuperation de toutes les données pour creer un produit
            Produit pToSend = new Produit(this.nomDuProduit.field.getText(),0);//ici on fait un debut de produit pour le remplire avec les autre données par la suite
            pToSend.setLargeur(Float.parseFloat(this.largeurTextField.getText()));
            pToSend.setLongueur(Float.parseFloat(this.longueurTextField.getText()));
            pToSend.setPoids(Float.parseFloat(this.poidsTextField.getText()));

            pToSend.setCout(Float.parseFloat(this.coutUnitaireTextField.getText())); //float pour un prix
            pToSend.setQuantite(Integer.parseInt(this.quantiteTextField.getText()));
            //pToSend.setNomdelaboutique(this.nomDeLaBoutiqueTextField.getText());

            pToSend.setCodeBarre(Integer.parseInt(this.codeBarreTextField.getText()));
            //pToSend.setCatProd(this.categorieBox.getSelectedItem());

                this.pDAO.create(pToSend);
            }
        });
    }


}
