package vue;

import net.miginfocom.swing.MigLayout;
import utils.Constants;
import utils.daoUtils.ClientDAO;
import utils.daoUtils.ProfilDAO;
import utils.daoUtils.BoutiqueDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class ManageParcours extends JPanel{
    private ProfilDAO profDAO;
    private BoutiqueDAO bDAO;

    public ManageParcours(){
        this.setLayout(new BorderLayout());

        //ajout panneau des actions
        this.add(getActionPane(), BorderLayout.NORTH);
        this.bDAO = new BoutiqueDAO(Constants.conServ);
        this.profDAO = new ProfilDAO(Constants.conServ);
        //this.add(pane, BorderLayout.WEST);

    }

    private JPanel getActionPane()
    {
        JPanel panel = new JPanel();
        JButton btn1 = new JButton("Create Parcours");
        //JButton btn2 = new JButton("Delete profils");

        btn1.addActionListener(e -> this.profDAO.getProfilsWithoutParcours());
        //btn2.addActionListener(e -> this.bDAO.getStoreWithCategory());

        panel.add(btn1);
        //panel.add(btn2);


        return panel;
    }

}
