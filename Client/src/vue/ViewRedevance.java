package vue;

import R3.AlgorithmFee;
import com.toedter.calendar.*;
import model.Boutique;
import model.Redevance;
import net.miginfocom.swing.MigLayout;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.RedevanceDAO;
import utils.MyListModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ViewRedevance extends JPanel{
    private ArrayList<Boutique> listeBoutique;
    private BoutiqueDAO bDAO=new BoutiqueDAO();
    /**
     * Weight of the product
     */
    private JLabel Mot;
    ArrayList<Redevance> list=new ArrayList<>();
    /**
     * List for scroll pane
     */
    JList<Redevance> jlist=new JList<>();
    /**
     * Scroll pane for the products
     */
    JScrollPane j=new JScrollPane(jlist);

    public ViewRedevance() {
        //this.setSize(new Dimension(500, 420));
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("inset 5", "[fill, grow]", "[fill, grow]"));
        this.Mot = new JLabel("Redevances de toutes les boutiques" );

        JButton btn = new JButton("Calculer les redevances");
        btn.addActionListener((event) -> {
               // AlgorithmFee algo=new AlgorithmFee();
               // algo.assignFeeToStore();

        });
        panel.add(btn);

        Calendar calendar = Calendar.getInstance();
        Date auj=calendar.getTime();
        SimpleDateFormat formatmois = new SimpleDateFormat("MM");
        SimpleDateFormat formatannee = new SimpleDateFormat("yyyy");

        JYearChooser monannee= new JYearChooser();
        JMonthChooser monmois= new JMonthChooser();

        RedevanceDAO rDAO = new RedevanceDAO();
        ArrayList<Redevance> list2;
        list2 = rDAO.findFromReference(monannee.getYear(),monmois.getMonth());
        MyListModel<Redevance> listModel2 = new MyListModel(list2);
        JList<Redevance> jlist2 = new JList(listModel2);
        jlist2.setFixedCellWidth(90);
System.out.println(list2.size());
        jlist2.setListData(list2.toArray(new Redevance[list2.size()]));
        jlist=jlist2;
        j.setViewportView(jlist2);
        panel.repaint();
        panel.revalidate();



        j.setPreferredSize(new Dimension(300, 200));


        JButton valid= new JButton("Valider");
        valid.addActionListener((event) -> {

                    try {


                        ArrayList<Redevance> list1;
                        list1 = rDAO.findFromReference(monannee.getYear(),monmois.getMonth());
                        MyListModel<Redevance> listModel1 = new MyListModel(list1);
                        JList<Redevance> jlist1 = new JList(listModel1);
                        jlist1.setFixedCellWidth(90);
                        jlist1.setListData(list1.toArray(new Redevance[list1.size()]));
                        j.setViewportView(jlist1);
                        jlist=jlist1;

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    panel.repaint();
                    panel.revalidate();
                });
        panel.add(this.Mot, "cell 0 0");
        panel.add(monannee, "cell 0 1");
        panel.add(monmois, "cell 1 1");
        panel.add(valid, "cell 2 1");

        //panel.add(j, "cell 0 2 4 2");
        JButton detail= new JButton("Details sur la redevance");
        detail.addActionListener((event2) -> {
            DetailFee df = new DetailFee(jlist.getSelectedValue(),jlist.getSelectedValue().getId_boutique().getEmplacement());
            SwingUtilities.invokeLater(df);
        });


        panel.add(j, "cell 0 2 3 4");
        panel.add(detail, "cell 0 6 3 2");
        this.add(panel);


    }
    public void run() {
        this.setVisible(true);
    }
}
