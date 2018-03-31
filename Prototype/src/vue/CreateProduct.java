package vue;

import model.Produit;
import net.miginfocom.swing.MigLayout;
import utils.Constants;
import utils.daoUtils.ProduitDAO;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CreateProduct extends JPanel{

    private TextLabel <JTextField> nomDuProduit;
    private JTextField nomDuProduitTextField;

    private TextLabel <JComboBox<String>> categorie;
    private JComboBox<String> categorieBox;

    private TextLabel <JTextField> codeBarre;
    private JTextField codeBarreTextField;

    private TextLabel <JTextField> nomDeLaBoutique;
    private JTextField nomDeLaBoutiqueTextField;

    private TextLabel <JTextField> quantite;
    private JTextField quantiteTextField;

    private TextLabel <JTextField> coutUnitaire;
    private JTextField coutUnitaireTextField;

    private TextLabel <JTextField> largeur;
    private JTextField largeurTextField;

    private TextLabel <JTextField> longueur;
    private JTextField longueurTextField;

    private TextLabel <JTextField> poids;
    private JTextField poidsTextField;

    private ProduitDAO pDAO;


    private JButton validerBoutton;

    public CreateProduct(){
        this.view();
        this.pDAO = new ProduitDAO(Constants.conServ);
    }

    private void view (){

        this.setLayout(new MigLayout("inset 30 ", "[fill, grow]", "[fill, grow]"));

        nomDuProduitTextField = new JTextField(20);
        nomDuProduitTextField.setPreferredSize(new Dimension(300,50));
        nomDuProduit = new TextLabel (nomDuProduitTextField, new JLabel("Nom du Produit"));
        this.add(nomDuProduit, "cell 0 1 1 1");

        categorieBox = new JComboBox<String>();
        categorieBox.setPreferredSize(new Dimension(250,50));
        categorie = new TextLabel(categorieBox, new JLabel("Categorie"));
        this.add(categorie, "cell 4 1 1 1");

        codeBarreTextField = new JTextField(20);
        codeBarreTextField.setPreferredSize(new Dimension(300,50));
        codeBarre = new TextLabel(codeBarreTextField, new JLabel("Code Barre"));
        this.add(codeBarre, "cell 0 3 1 1");


        /*nomDeLaBoutiqueTextField = new JTextField(20);
        nomDeLaBoutiqueTextField.setPreferredSize(new Dimension(300,50));
        nomDeLaBoutique = new TextLabel(nomDeLaBoutiqueTextField, new JLabel("Nom de la Boutique"));
        this.add(nomDeLaBoutique, "cell 0 3 1 1");*/

        quantiteTextField = new JTextField(20);
        quantiteTextField.setPreferredSize(new Dimension(300,50));
        quantite = new TextLabel(quantiteTextField, new JLabel("Quantite"));
        this.add(quantite, "cell 2 3 1 1");

        coutUnitaireTextField = new JTextField(20);
        coutUnitaireTextField.setPreferredSize(new Dimension(300,50));
        coutUnitaire = new TextLabel(coutUnitaireTextField, new JLabel("Cout Unitaire"));
        this.add(coutUnitaire, "cell 4 3 1 1");

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
        });
    }


}
