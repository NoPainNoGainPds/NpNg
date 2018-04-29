package vue;

import com.toedter.calendar.*;
import model.Boutique;
import model.Emplacement;
import model.Redevance;
import model.StockEntree;
import net.miginfocom.swing.MigLayout;
import utils.Constants;
import utils.daoUtils.RedevanceDAO;
import utils.MyListModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;


public class ViewRedevance extends JPanel{
    private ArrayList<Boutique> listeBoutique;
    private ArrayList<Emplacement> listeEmplacement;
    private ArrayList<Redevance> listeRedevance;
    /**
     * Weight of the product
     */
    private JLabel Mot;
    ArrayList<String> list=new ArrayList<>();
    /**
     * List for scroll pane
     */
    JList<String> jlist=new JList<>();
    /**
     * Scroll pane for the products
     */
    JScrollPane j=new JScrollPane(jlist);

    public ViewRedevance() {
        this.setSize(new Dimension(500, 420));
        JPanel panel = new JPanel();
        this.Mot = new JLabel("Redevances de toutes les boutiques" );



        RedevanceDAO redevance = new RedevanceDAO();
        ArrayList<Redevance> list1=new ArrayList<>();
        list1 = redevance.findFromReference();
        MyListModel<Redevance> listModel1 = new MyListModel(list1);
        JList<Redevance> jlist1 = new JList(listModel1);
        jlist1.setFixedCellWidth(90);

        jlist1.setListData(list1.toArray(new Redevance[list1.size()]));
        j.setViewportView(jlist1);
        j.setPreferredSize(new Dimension(300, 200));

        JYearChooser monannee= new JYearChooser();
        JMonthChooser monmois= new JMonthChooser();
        JButton valid= new JButton("Valider");
        valid.addActionListener((event) -> {
            System.out.println(monannee.getYear()+" "+monmois.getMonth());
                });

        panel.add(monannee);
        panel.add(monmois);
        panel.add(valid);
        panel.add(this.Mot);
        panel.add(j, BorderLayout.WEST);
        this.add(panel);


    }
    public void run() {
        this.setVisible(true);
    }
}
