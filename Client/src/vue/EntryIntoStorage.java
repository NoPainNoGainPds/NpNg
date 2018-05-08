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
    /**
     * Textfield to enter the date
     */
    private TextLabel <JTextField> dateEntreeStock;
    private JTextField dateEntreeStockTextField;
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

    private TextLabel<JTextField> bonLivraison;
    private JTextField bonLivraisonTextField;

     /**
     * Valdation button
     */
    private JButton validerBoutton;

    private JButton addLineForm;

    private JPanel FormPan;

    private JPanel FormPanLine;

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
            this.setLayout(new BorderLayout());

            FormPan = new JPanel();
            FormPan.setLayout(new MigLayout("inset 0 20 20 20 ", "[fill, grow]", "[fill, grow]"));

            bonLivraisonTextField = new JTextField(20);
            bonLivraisonTextField.setPreferredSize(new Dimension(250, 50));
            bonLivraison = new TextLabel(bonLivraisonTextField, new JLabel ("Numero du bon de livraison"));
            FormPan.add(bonLivraison);

            dateEntreeStockTextField = new JTextField(20);
            dateEntreeStockTextField.setPreferredSize(new Dimension(250,50));
            dateEntreeStock = new TextLabel(dateEntreeStockTextField, new JLabel("Date entree du stock"));
            FormPan.add(dateEntreeStock);

            fournisseurTextField = new JTextField(20);
            fournisseurTextField.setPreferredSize(new Dimension(250,50));
            fournisseur = new TextLabel(fournisseurTextField, new JLabel("Fournisseur"));
            FormPan.add(fournisseur);

            nomDeLaBoutiqueBox = new JComboBox<Boutique>();
            nomDeLaBoutiqueBox.setPreferredSize(new Dimension(250, 50));
            nomDeLaBoutique = new TextLabel (nomDeLaBoutiqueBox, new JLabel("Nom de la boutique"));
            FormPan.add(nomDeLaBoutique, "wrap");

            validerBoutton = new JButton("Valider");
            addLineForm = new JButton("Ajout ligne");

            FormPanLine = new JPanel();
            FormPanLine.setLayout(new MigLayout("inset 0 20 20 20 ", "[fill, grow]", "[fill, grow]"));
            ArrayList<EntryIntoStorageForm> liste = new ArrayList<>();
            EntryIntoStorageForm panTemp = new EntryIntoStorageForm();
            liste.add(panTemp);
            FormPanLine.add(panTemp, "wrap");

            addLineForm.addActionListener(e -> {
                EntryIntoStorageForm panTemp1 = new EntryIntoStorageForm();
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
            this.validerBoutton.addActionListener(event ->{
                StockEntree stToSend = new StockEntree();

                //stToSend.setId_boutique();) probleme demande Id et pas nom
                stToSend.setQuantite(Integer.parseInt(this.quantiteTextField.getText()));
                //stToSend.setDate(); comment on fait les dates ?
                stToSend.setMontant(Integer.parseInt(this.montantTextField.getText()));

            });
        }*/
    }
