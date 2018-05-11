package vue;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import model.BonLivraison;
import model.Boutique;
import model.StockEntree;
import net.miginfocom.swing.MigLayout;
import utils.Constants;
import utils.daoUtils.BonLivraisonDAO;
import utils.daoUtils.ProduitDAO;
import utils.daoUtils.StockEntreeDAO;

import java.awt.*;
import java.text.DateFormat;
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
    private TextLabel <JDateChooser> dateEntreeStock;
    private JDateChooser dateEntreeStockDate;
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

    private ArrayList<EntryIntoStorageForm> liste;

    private StockEntreeDAO seDAO;

    private BonLivraisonDAO blDAO;

    /**
     * Constructor
     */
    public EntryIntoStorage(){
        this.View();
        this.seDAO = new StockEntreeDAO();
        this.blDAO = new BonLivraisonDAO();
        this.controler();
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

            dateEntreeStockDate = new JDateChooser();
            dateEntreeStockDate.setPreferredSize(new Dimension(250,50));
            dateEntreeStock = new TextLabel(dateEntreeStockDate, new JLabel("Date entree du stock"));
            FormPan.add(dateEntreeStock);

            fournisseurTextField = new JTextField(20);
            fournisseurTextField.setPreferredSize(new Dimension(250,50));
            fournisseur = new TextLabel(fournisseurTextField, new JLabel("id du Fournisseur"));
            FormPan.add(fournisseur);

            nomDeLaBoutiqueBox = new JComboBox<Boutique>();
            nomDeLaBoutiqueBox.setPreferredSize(new Dimension(250, 50));
            nomDeLaBoutique = new TextLabel (nomDeLaBoutiqueBox, new JLabel("Nom de la boutique"));
            FormPan.add(nomDeLaBoutique, "wrap");

            validerBoutton = new JButton("Valider");
            addLineForm = new JButton("Ajout ligne");

            FormPanLine = new JPanel();
            FormPanLine.setLayout(new MigLayout("inset 0 20 20 20 ", "[fill, grow]", "[fill, grow]"));
            liste = new ArrayList<>();
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

        private void controler(){
            this.validerBoutton.addActionListener(event ->{
                int valide = 0;
                if(this.bonLivraison.field.getText().equals(""))
                {
                    this.bonLivraison.field.setBackground(Color.RED);
                    valide++;
                }
                if(this.dateEntreeStockDate.getDate().equals(""))
                {
                    this.dateEntreeStockDate.setBackground(Color.RED);
                    valide++;
                }
                if(this.fournisseurTextField.getText().equals(""))
                {
                    this.fournisseurTextField.setBackground(Color.RED);
                    valide++;
                }
                //Manque combobox boutique
                for (int i = 0; i < liste.size(); i++){
                    if(liste.get(i).getIdProduitTextField().getText().equals("")){
                        liste.get(i).getIdProduitTextField().setBackground(Color.RED);
                        valide ++;
                    }
                    if(liste.get(i).getNomProduitTextField().getText().equals("")){
                        liste.get(i).getNomProduitTextField().setBackground(Color.RED);
                        valide ++;
                    }
                    if(liste.get(i).getQuantiteTextField().getText().equals("")){
                        liste.get(i).getQuantiteTextField().setBackground(Color.RED);
                        valide ++;
                    }
                    if(liste.get(i).getCoutUnitaireTextField().getText().equals("")){
                        liste.get(i).getCoutUnitaireTextField().setBackground(Color.RED);
                        valide ++;
                    }
                }

                if (valide==0){
                    //StockEntree stToSend = new StockEntree();
                    BonLivraison blToSend = new BonLivraison();

                    blToSend.setDate_livraison(this.dateEntreeStockDate.getDate());
                    blToSend.setId_fournisseur(Integer.parseInt(this.fournisseurTextField.getText()));
                    //blToSend.setMontant_livraison(Float.parseFloat(this.montantTextField.getText()));

                    for (int i = 0; i < liste.size(); i++){
                        StockEntree seToSend = new StockEntree();
                            seToSend.setQuantite(Integer.parseInt(this.liste.get(i).getQuantiteTextField().getText()));
                            seToSend.setId_produit(Integer.parseInt(this.liste.get(i).getIdProduitTextField().getText()));
                        this.seDAO.create(seToSend);
                    }
                    this.blDAO.create(blToSend);
                }
            });
        }
    }
