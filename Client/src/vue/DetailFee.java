//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package vue;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

import javafx.scene.control.RadioButton;
import model.*;
import net.miginfocom.swing.MigLayout;
import utils.MyListModel;

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
        this.setSize(new Dimension(700, 700));
        this.redevance = redevance;
        this.boutique=boutique;
        this.emplacement=emplacement;
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));

        this.img = Toolkit.getDefaultToolkit().getImage(redevance.getId_boutique().getLogo());
        this.logo = new ImageComponent(true);
        this.logo.setImage(this.img);
        panel.add(this.logo, "cell 0 0 3 3, split 2");

        SimpleDateFormat formater = new SimpleDateFormat("MMMMM yyyy");

        this.nom_boutique = new JLabel("Redevance de " + redevance.getNom_boutique() + " du mois de "+ formater.format(redevance.getDate_redevance()));
        panel.add(this.nom_boutique, "cell 0 0");

        this.montant= new JLabel("Montant de la redevance: " + redevance.getMontant_redevance());
        panel.add(this.montant, "cell 0 1 2 2");

        this.nom_emplacement= new JLabel("Nom de l'emplacement: " + emplacement.getNom());
        panel.add(this.nom_emplacement, "cell 0 3");

        this.superficie = new JLabel("Superficie de l'emplacement: " + emplacement.getSuperficie());
        panel.add(this.superficie, "cell 0 4");

        this.categorie_emplacement = new JLabel("Categorie de l'emplacement: " + emplacement.getCat());
        panel.add(this.categorie_emplacement, "cell 0 5");

        this.abrev =new JLabel ("s: superficie de l'emplacement / c: categorie de l'emplacement");
        panel.add(this.abrev, "cell 2 1");
        this.formule = new JLabel ("Formule de calcul: (s*c)*(1-s/10000)");
        panel.add(this.formule, "cell 2 2");
this.add(panel);

    }

    /**
     * Run method
     */
    public void run() {
        this.setVisible(true);
    }
}

