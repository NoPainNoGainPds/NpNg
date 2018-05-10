//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

import R3.Facture;
import javafx.scene.control.RadioButton;
import model.*;
import net.miginfocom.swing.MigLayout;
import utils.MyListModel;

import utils.daoUtils.RedevanceDAO;
import utils.daoUtils.StockEntreeDAO;
import utils.daoUtils.StockSortieDAO;

/**
 * Represents the view which include product's details
 */
public class DetailFee extends JFrame implements Runnable {
    /**
     * The store
     */
    private Redevance redevance;
    private Boutique boutique;
    private Emplacement emplacement;
    private JLabel nom_boutique;
    private JLabel nom_emplacement;
    private JLabel montant;
    private JLabel superficie;

    private JLabel categorie_emplacement;
    private JLabel formule;
    private JLabel abrev;
    private JLabel msgError;

    private TextLabel <JComboBox<String>> Dest;
    private JTextField DestField;

    private Image img;
    /**
     * Logo of the store
     */
    private ImageComponent logo;
    /**

    /**
     * Constructor
     * @param redevance The fee
     */
    public DetailFee(Redevance redevance, Emplacement emplacement) {
        super("Detail de la redevance");
        this.setSize(new Dimension(600, 700));
        this.redevance = redevance;
        this.emplacement=emplacement;
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        JPanel panel1 =new JPanel();
        panel1.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        JPanel panel2=new JPanel();
        panel2.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        panel1.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        JPanel panel3=new JPanel();
        panel3.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        this.img = Toolkit.getDefaultToolkit().getImage(redevance.getId_boutique().getLogo());
        this.logo = new ImageComponent(true);
        this.logo.setImage(this.img);
        panel.add(this.logo, "cell 0 0 3 3, split 2");

        SimpleDateFormat formater = new SimpleDateFormat("MMMMM yyyy");

        this.nom_boutique = new JLabel("Redevance de " + redevance.getNom_boutique() + " du mois de "+ formater.format(redevance.getDate_redevance()));
        panel.add(this.nom_boutique, "cell 1 0 3 3");


        this.nom_emplacement= new JLabel("Nom: " + emplacement.getNom());
        panel1.add(this.nom_emplacement, "cell 0 0 2 1");

        this.superficie = new JLabel("Superficie: " + emplacement.getSuperficie());
        panel1.add(this.superficie, "cell 0 1");

        this.categorie_emplacement = new JLabel("Categorie: " + emplacement.getCat());
        panel1.add(this.categorie_emplacement, "cell 1 1");

        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Informations sur l'emplacement"));



        this.abrev =new JLabel ("s: superficie de l'emplacement / p: prix au m2");
        panel2.add(this.abrev, "cell 0 0");
        this.formule = new JLabel ("Formule de calcul: (s*p)*(1-s/80000)");
        this.formule.setForeground(Color.red);
        panel2.add(this.formule, "cell 0 1");

        Object[][] donnees = {
                {"*", "70\u20AC"},
                {"A", "62,5\u20AC"},
                {"B", "55\u20AC"},
                {"C", "47,5\u20AC"},
                {"D", "40\u20AC"},

        };

        String[] entetes = {"Cat. emplacement", "Prix au m2"};

        JTable tableau = new JTable(donnees, entetes);
        JScrollPane j=new JScrollPane(tableau);
        j.setPreferredSize(new Dimension(200,59));

        panel2.add(j, "cell 1 0 10 10");
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Formule"));


        this.montant= new JLabel( +redevance.getMontant_redevance()+"\u20AC");
        this.montant.setHorizontalAlignment(JLabel.CENTER);
        this.montant.setVerticalAlignment(JLabel.CENTER);
        this.montant.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Montant de la redevance"));



        panel.add(panel1, "cell 0 3 1 1");
        panel.add(panel2, "cell 0 4 1 1");
        panel.add(this.montant, "cell 0 7");
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.add(panel);


        DestField = new JTextField(20);
        DestField.setText("C:\\\\Users\\\\remys\\\\Documents/facture.pdf");
        Dest = new TextLabel (DestField, new JLabel("Destination Facture"));
        panel.add(Dest, "cell 0 8 3 1");

        JButton valid= new JButton("Valider");
        valid.addActionListener((event) -> {
            //File file = new File(this.DestField.getText());
            //file.getParentFile().mkdirs();

            RedevanceDAO rDAO=new RedevanceDAO();
            rDAO.generPDF(this.DestField.getText(), redevance.getid_Redevance());
        });

        panel.add(valid,"cell 4 8 1 1");
    }



    /**
     * Run method
     */
    public void run() {
        this.setVisible(true);
    }
}

