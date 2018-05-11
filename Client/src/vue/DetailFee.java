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
 * Represents the view which include fee's details
 */
public class DetailFee extends JFrame implements Runnable {
    /**
     * The fee
     */
    private Redevance redevance;
    /**
     * The location
     */
    private Emplacement emplacement;
    /**
     * The name of the store
     */
    private JLabel nom_boutique;
    /**
     * The name of the location
     */
    private JLabel nom_emplacement;
    /**
     * The amount of the fee
     */
    private JLabel montant;
    /**
     * The area of the location
     */
    private JLabel superficie;
    /**
     * The location category
     */
    private JLabel categorie_emplacement;
    /**
     * Number of visitors
     */
    private JLabel visiteurs;
    /**
     * The expression to calculate a fee
     */
    private JLabel formule;
    /**
     * The informations for the expression
     */
    private JLabel abrev;    private JLabel abrev1;    private JLabel abrev2;
    /**
     * The name of the PDF
     */
    private TextLabel <JTextField> NomPdf;
    private JTextField NomPdfField;
    /**
     * The logo of the store
     */
    private Image img;
    /**
     * Logo of the store
     */
    private ImageComponent logo;
    /**

    /**
     * Constructor
     * @param redevance The fee
     * @param emplacement The location
     */
    public DetailFee(Redevance redevance, Emplacement emplacement) {
        super("Detail de la redevance");
        this.setSize(new Dimension(600, 700));

        this.redevance = redevance;
        this.emplacement = emplacement;

        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        JPanel panel1 =new JPanel();
        panel1.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        JPanel panel2=new JPanel();
        panel2.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        panel1.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        JPanel panel4=new JPanel();
        panel4.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));

        this.img = Toolkit.getDefaultToolkit().getImage(redevance.getId_boutique().getLogo());
        this.logo = new ImageComponent(true);
        this.logo.setImage(this.img);


        SimpleDateFormat formater = new SimpleDateFormat("MMMMM yyyy");

        this.nom_boutique = new JLabel("Redevance de " + redevance.getNom_boutique() + " du mois de "+ formater.format(redevance.getDate_redevance()));
        this.nom_emplacement= new JLabel("Nom: " + emplacement.getNom());
        this.superficie = new JLabel("Superficie: " + emplacement.getSuperficie()+" m2");
        this.categorie_emplacement = new JLabel("Categorie: " + emplacement.getCat());
        this.visiteurs = new JLabel ("Frequentation du mois: " + redevance.getFreq() +" visiteurs");
        this.abrev =new JLabel ("s: superficie de l'emplacement");
        this.abrev1 =new JLabel ("c: prix cat. emplacement");
        this.abrev2 =new JLabel ("f: nb visiteurs");
        this.formule = new JLabel ("Formule de calcul: (s*c)*(1-s/70000+f/s)");
        this.formule.setForeground(Color.red);


        Object[][] donnees = {
                {"*", "65\u20AC"},
                {"A", "57,5\u20AC"},
                {"B", "50\u20AC"},
                {"C", "42,5\u20AC"},
                {"D", "40\u20AC"},

        };
        String[] entetes = {"Cat. emplacement", "Prix"};
        JTable tableau = new JTable(donnees, entetes);
        JScrollPane j=new JScrollPane(tableau);
        j.setPreferredSize(new Dimension(200,59));


        this.montant= new JLabel( +redevance.getMontant_redevance()+"\u20AC");
        this.montant.setHorizontalAlignment(JLabel.CENTER);
        this.montant.setVerticalAlignment(JLabel.CENTER);
        this.montant.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Montant de la redevance"));



        NomPdfField = new JTextField(20);
        NomPdfField.setText("facture.pdf");
        NomPdf = new TextLabel (NomPdfField, new JLabel("Nom du fichier: "));


        JButton valid= new JButton("Valider");
        valid.addActionListener((event) -> {
            //File file = new File(this.DestField.getText());
            //file.getParentFile().mkdirs();

            RedevanceDAO rDAO=new RedevanceDAO();
            rDAO.generPDF(this.NomPdfField.getText(), redevance.getid_Redevance());
        });


        panel.add(this.logo, "cell 0 0 3 3, split 2");
        panel.add(this.nom_boutique, "cell 3 0 3 3");
        panel1.add(this.nom_emplacement, "cell 0 0");
        panel1.add(this.superficie, "cell 1 0");
        panel1.add(this.categorie_emplacement, "cell 0 1");
        panel1.add(this.visiteurs, "cell 1 1");

        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Informations"));

        panel2.add(this.abrev, "cell 0 0");
        panel2.add(this.abrev1, "cell 0 1");
        panel2.add(this.abrev2, "cell 0 2");
        panel2.add(this.formule, "cell 0 3");
        panel2.add(j, "cell 1 0 5 5");
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Formule"));
        panel4.add(NomPdf, "cell 0 0 2 1");
        panel4.add(valid, "cell 0 1 1 1");
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Facture"));

        panel.add(panel1, "cell 0 4 3 1");
        panel.add(panel2, "cell 0 5 3 1");
        panel.add(this.montant, "cell 0 6 3 1");
        panel.add(panel4,"cell 0 7 3 1");
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.add(panel);


    }



    /**
     * Run method
     */
    public void run() {
        this.setVisible(true);
    }
}

