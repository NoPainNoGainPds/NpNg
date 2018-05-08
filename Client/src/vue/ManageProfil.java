package vue;

import model.Client;
import model.Profil;
import net.miginfocom.swing.MigLayout;
import utils.Constants;
import utils.daoUtils.ClientDAO;
import utils.daoUtils.ProfilDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class ManageProfil extends JPanel{
    private ArrayList<Profil> liste;
    private ArrayList<Client> listeClient;
    private ClientView clientView;
    private ClientDAO cDao;
    private ProfilDAO profDAO;
    private Image imgProf;
    public ManageProfil(){
        this.setLayout(new BorderLayout());
        this.cDao = new ClientDAO(Constants.conServ);
        this.profDAO = new ProfilDAO(Constants.conServ);
        System.out.println("demande client");
        this.listeClient = this.cDao.findFromReference();
        System.out.println("ici client");
        JList<Client> listeClient = new JList<Client>(this.listeClient.toArray(new Client[0]));
        JScrollPane pane = new JScrollPane(listeClient);
        //ajout panneau des actions
        this.add(getActionPane(), BorderLayout.NORTH);
        this.add(pane, BorderLayout.WEST);

        listeClient.addListSelectionListener((ListSelectionEvent listSelectionEvent) ->
            {
                //au clique, afficher les achats du client.
                Client c = listeClient.getSelectedValue();
                this.clientView.setClient(c);
            }
        );
        this.clientView = new ClientView(profDAO);
        this.add(this.clientView,BorderLayout.CENTER);
    }
    private JPanel getActionPane()
    {
        JPanel panel = new JPanel();
        JButton btn1 = new JButton("Attribuer Profils");
        JButton btn2 = new JButton("Supprimer profils");
        JButton btn3 = new JButton("autre JSP pour le moment");

        btn1.addActionListener(e -> this.cDao.AttrProf());
        btn2.addActionListener(e -> this.cDao.delAllProfil());
        btn3.addActionListener(e-> this.cDao.attPurchase());

        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);

        return panel;
    }
}
