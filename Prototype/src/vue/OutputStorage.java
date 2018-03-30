package vue;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class OutputStorage extends JPanel{

    private JLabel nomProduitLabel;
    private TextLabel <JTextField> nomDeLaBoutique;
    private TextLabel <JTextField> dateSortieStock;
    private TextLabel <JTextField> quantite;
    private TextLabel <JTextField> montant;
    private TextLabel <JTextField> client;
    private TextLabel <JTextField> coutUnitaire;


    private ImageComponent PhotoProduit;
    private Image img;


    private JButton validerBoutton;

    public OutputStorage(){
        this.View();
    }

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

        dateSortieStock = new TextLabel(new JTextField(20), new JLabel("Date Achat"));
        this.add(dateSortieStock, "cell 2 2 1 1");

        quantite = new TextLabel(new JTextField(20), new JLabel("Quantite"));
        this.add(quantite, "cell 4 2 1 1");


        montant = new TextLabel(new JTextField(20), new JLabel("Montant"));
        this.add(montant, "cell 0 4 1 1");

        client = new TextLabel(new JTextField(20), new JLabel("Fournisseur"));
        this.add(client, "cell 2 4 1 1");

        coutUnitaire = new TextLabel(new JTextField(20), new JLabel("Cout Unitaire"));
        this.add(coutUnitaire, "cell 4 4 1 1");


        validerBoutton = new JButton("Valider");

        JPanel Buttonpan = new JPanel(new MigLayout("", "[center, grow]"));
        Buttonpan.add(validerBoutton);
        this.add(Buttonpan, "dock south");


    }
}