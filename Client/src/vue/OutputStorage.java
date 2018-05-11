package vue;

import com.toedter.calendar.JDateChooser;
import model.Boutique;
import model.StockSortie;
import model.CauseSortieStock;
import net.miginfocom.swing.MigLayout;
import sun.awt.CausedFocusEvent;
import utils.Constants;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.CauseSortieStockDAO;
import utils.daoUtils.StockSortieDAO;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
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
    private TextLabel <JDateChooser> dateSortieStock;
    private JDateChooser dateSortieStockDate;

    private ArrayList<OutputStorageForm> liste;


    private StockSortieDAO ssDAO;

    private BoutiqueDAO bDAO;

    private CauseSortieStockDAO cDAO;

    private JButton validerBoutton;

    private JButton addLineForm;

    private JPanel FormPan;

    private JPanel FormPanLine;

    /**
     * Constructor
     */
    public OutputStorage(){
        this.ssDAO = new StockSortieDAO();
        this.bDAO = new BoutiqueDAO();
        this.cDAO = new CauseSortieStockDAO();
        this.View();
        this.controler();
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
        ArrayList<Boutique> listB = this.bDAO.findFromReference();
        nomDeLaBoutiqueBox.setModel(new DefaultComboBoxModel<Boutique>(listB.toArray(new Boutique[listB.size()])));
        /*for(int i = 0; i < listB.size(); i++){
            nomDeLaBoutiqueBox.addItem("Nom :" +listB.get(i).getNom()+"  Id : "+ listB.get(i).getId());
        }*/
        nomDeLaBoutique = new TextLabel (nomDeLaBoutiqueBox, new JLabel("Nom de la boutique"));
        FormPan.add(nomDeLaBoutique);

        dateSortieStockDate = new JDateChooser();
        dateSortieStockDate.setPreferredSize(new Dimension(250,50));
        dateSortieStock = new TextLabel(dateSortieStockDate, new JLabel("Date sortie stock"));
        FormPan.add(dateSortieStock);

        causeBox = new JComboBox<CauseSortieStock>();
        causeBox.setPreferredSize(new Dimension(250,50));
        ArrayList<CauseSortieStock> listeC = this.cDAO.findFromReference();
        causeBox.setModel(new DefaultComboBoxModel<CauseSortieStock>(listeC.toArray(new CauseSortieStock[listeC.size()])));
        cause = new TextLabel(causeBox, new JLabel("Cause de la sortie de stock"));
        FormPan.add(cause, "wrap");


        validerBoutton = new JButton("Valider");
        addLineForm = new JButton("Ajout ligne");

        FormPanLine = new JPanel();
        FormPanLine.setLayout(new MigLayout("inset 0 20 20 20 ", "[fill, grow]", "[fill, grow]"));
        liste = new ArrayList<>();
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

    private void controler(){
        this.validerBoutton.addActionListener(event ->
        {
            int valide = 0;
            if(this.dateSortieStockDate.getDate().equals("")){
                this.dateSortieStockDate.setBackground(Color.RED);
                valide++;
            }

            for(int i = 0; i < liste.size(); i++ ) {
                if(liste.get(i).getIdProduitTextField().getText().equals("")){
                    liste.get(i).getIdProduitTextField().setBackground(Color.RED);
                    valide++;
                }
                if(liste.get(i).getNomProduitTextField().getText().equals("")){
                    liste.get(i).getNomProduitTextField().setBackground(Color.RED);
                    valide++;
                }
                if(liste.get(i).getQuantiteTextField().getText().equals("")){
                    liste.get(i).getQuantiteTextField().setBackground(Color.RED);
                    valide++;
                }
            }
            if(valide==0) {
                for(int i = 0; i < liste.size(); i++ ) {
                    StockSortie ssToSend = new StockSortie();
                        ssToSend.setId_produit(Integer.parseInt(liste.get(i).getIdProduitTextField().getText()));
                        ssToSend.setQuantite(Integer.parseInt(liste.get(i).getQuantiteTextField().getText()));
                        ssToSend.setDate(this.dateSortieStockDate.getDate());
                        ssToSend.setId_boutique(this.nomDeLaBoutique.field.getItemAt(this.nomDeLaBoutique.field.getSelectedIndex()).getId());
                        ssToSend.setId_cause(this.cause.field.getItemAt(this.cause.field.getSelectedIndex()).getId_cause());
                    this.ssDAO.create(ssToSend);
                }
            }
        });
    }
}
