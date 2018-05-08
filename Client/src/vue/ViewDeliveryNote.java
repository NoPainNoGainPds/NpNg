package vue;

import R3.AlgorithmFee;
import com.toedter.calendar.*;
import model.Boutique;
import net.miginfocom.swing.MigLayout;
import utils.MyListModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map;


public class ViewDeliveryNote extends JPanel {

    private TextLabel <JComboBox<Boutique>> nomDeLaBoutique;
    private JComboBox<Boutique> nomDeLaBoutiqueBox;

    private TextLabel <JTextField> dateEntreeStock;
    private JTextField dateEntreeStockTextField;



    public ViewDeliveryNote(){

        this.setLayout(new BorderLayout());

        JPanel panBouton = new JPanel();
        JButton boutonEntreeStock = new JButton ("Entree Stock");

        JButton boutonSortieStock = new JButton ("Sortie Stock");

        JButton boutonAffichageStock = new JButton ("Affichage Stock");

        panBouton.add(boutonEntreeStock);
        panBouton.add(boutonSortieStock);
        panBouton.add(boutonAffichageStock);



        JPanel panInfo = new JPanel();
        panInfo.setLayout(new CardLayout());

/*        JPanel panEntreeStock = new JPanel();

        nomDeLaBoutiqueBox = new JComboBox<Boutique>();
        nomDeLaBoutiqueBox.setPreferredSize(new Dimension(250,50));
        nomDeLaBoutique = new TextLabel (nomDeLaBoutiqueBox, new JLabel("Nom de la boutique"));
        panEntreeStock.add(nomDeLaBoutique, "cell 0 2 1 1");

        dateEntreeStockTextField = new JTextField(20);
        dateEntreeStockTextField.setPreferredSize(new Dimension(300,50));
        dateEntreeStock = new TextLabel(dateEntreeStockTextField, new JLabel("Date entree du stock"));
        panEntreeStock.add(dateEntreeStock, "cell 2 2 1 1");
*/

        //EntryIntoStorage EIS = new EntryIntoStorage();
        //panInfo.add(EIS);

        OutputStorage OS = new OutputStorage();
        panInfo.add(OS);


        this.add(panBouton, BorderLayout.NORTH);
        this.add(panInfo, BorderLayout.SOUTH);


    }

}
