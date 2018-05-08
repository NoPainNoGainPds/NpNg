package vue;

import com.toedter.calendar.*;
import model.Boutique;
import model.Emplacement;
import model.Redevance;
import net.miginfocom.swing.MigLayout;
import utils.daoUtils.RedevanceDAO;
import utils.MyListModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class ViewRedevance extends JPanel{

    RedevanceDAO rDAO = new RedevanceDAO();
    /**
     * Weight of the product
     */
    private JLabel titre;
    private JLabel lignevide;
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
        this.titre = new JLabel("Redevances de toutes les boutiques" );
        this.lignevide = new JLabel(" " );

        JYearChooser monannee= new JYearChooser();
        JMonthChooser monmois= new JMonthChooser();

        //initialize JScrollPane
        ArrayList<Redevance> list2;
        list2 = rDAO.findFromReference(monannee.getYear(),monmois.getMonth());
        MyListModel<Redevance> listModel2 = new MyListModel(list2);
        JList<Redevance> jlist2 = new JList(listModel2);
        jlist2.setFixedCellWidth(90);
        jlist2.setListData(list2.toArray(new Redevance[list2.size()]));
        jlist=jlist2;
        j.setViewportView(jlist2);
        panel.repaint();
        panel.revalidate();



        JButton valid= new JButton("Valider");
        JButton detail= new JButton("Details sur la redevance");
        JButton delete= new JButton("Supprimer");
        JButton calcul = new JButton("Calculer les redevances");


        calcul.addActionListener((event) -> {

            try {
                rDAO.calculFee();

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


        detail.addActionListener((event2) -> {
            DetailFee df = new DetailFee(jlist.getSelectedValue(),jlist.getSelectedValue().getId_boutique().getEmplacement());
            SwingUtilities.invokeLater(df);
        });


        delete.addActionListener((event3) -> {
            try {
                Redevance r = new Redevance(jlist.getSelectedValue().getid_Redevance());
                rDAO.delete(r);
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


        panel.add(calcul, "cell 0 0 5 1");
        panel.add(lignevide, "cell 0 1 ");
        panel.add(this.titre, "cell 0 2 5 1");

        panel.add(monannee, "cell 0 3 2 1");
        panel.add(monmois, "cell 3 3 2 1");
        panel.add(valid, "cell 4 3 1 1");

        j.setPreferredSize(new Dimension(550, 200));
        panel.add(j, "cell 0 4 5 4");

        panel.add(detail, "cell 0 8 3 1");
        panel.add(delete, "cell 3 8 2 1");
        this.add(panel);


    }



    public void run() {
        this.setVisible(true);
    }
}
