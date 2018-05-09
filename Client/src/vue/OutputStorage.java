package vue;

import model.Boutique;
import model.StockSortie;
import model.CauseSortieStock;
import net.miginfocom.swing.MigLayout;
import sun.awt.CausedFocusEvent;
import utils.Constants;
import utils.daoUtils.StockSortieDAO;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 *
 */
public class OutputStorage extends JPanel{

    private TextLabel<JComboBox<CauseSortieStock>>  cause;
    private JComboBox<CauseSortieStock> causeBox;

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


    private StockSortieDAO ssDAO;

    private JButton validerBoutton;

    private JButton addLineForm;

    private JPanel FormPan;

    private JPanel FormPanLine;

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

        this.setLayout(new BorderLayout());
        FormPan = new JPanel();
        FormPan.setLayout(new MigLayout("inset 0 20 20 20", "[fill, grow]", "[fill, grow]"));


        nomDeLaBoutiqueBox = new JComboBox<Boutique>();
        nomDeLaBoutiqueBox.setPreferredSize(new Dimension(250,50));
        nomDeLaBoutique = new TextLabel (nomDeLaBoutiqueBox, new JLabel("Nom de la boutique"));
        FormPan.add(nomDeLaBoutique);

        dateSortieStockTextField = new JTextField(20);
        dateSortieStockTextField.setPreferredSize(new Dimension(250,50));
        dateSortieStock = new TextLabel(dateSortieStockTextField, new JLabel("Date Achat"));
        FormPan.add(dateSortieStock);

        causeBox = new JComboBox<CauseSortieStock>();
        causeBox.setPreferredSize(new Dimension(250,50));
        cause = new TextLabel(causeBox, new JLabel("Cause de la sortie de stock"));
        FormPan.add(cause, "wrap");


        validerBoutton = new JButton("Valider");
        addLineForm = new JButton("Ajout ligne");

        FormPanLine = new JPanel();
        FormPanLine.setLayout(new MigLayout("inset 0 20 20 20 ", "[fill, grow]", "[fill, grow]"));
        ArrayList<OutputStorageForm> liste = new ArrayList<>();
        OutputStorageForm panTemp = new OutputStorageForm();
        liste.add(panTemp);
        FormPanLine.add(panTemp, "wrap");

        addLineForm.addActionListener(e -> {
            OutputStorageForm panTemp1 = new OutputStorageForm();
            liste.add(panTemp1);
            FormPanLine.add(panTemp1, "wrap");
            FormPanLine.repaint();
            FormPanLine.revalidate();
        });

        JPanel Buttonpan = new JPanel(new MigLayout("", "[center, grow]"));
        Buttonpan.add(addLineForm, "cell 0 2 1 1");
        Buttonpan.add(validerBoutton, "cell 2 2 1 1");

        this.add(FormPanLine, BorderLayout.CENTER);
        this.add(FormPan, BorderLayout.NORTH);
        this.add(Buttonpan, BorderLayout.SOUTH);
    }

    /*private void controler(){
        this.validerBoutton.addActionListener(event ->
        {
            StockSortie ssToSend = new StockSortie();
            //ssToSend.setDate(...);  Comment on fait la date lol
            //ssToSend.setId_boutique();  Erreur pcq on recupere un nom et pas l'id
            ssToSend.setQuantite(Integer.parseInt(this.quantiteTextField.getText()));


        });
    }*/
}
