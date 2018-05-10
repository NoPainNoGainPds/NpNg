package vue;

import model.Client;
import model.Profile;
import utils.Constants;
import utils.daoUtils.ClientDAO;
import utils.daoUtils.ProfilDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.ArrayList;

public class ManageProfil extends JPanel{
    private ArrayList<Profile> liste;
    private ArrayList<Client> listeClient;
    private ClientView clientView;
    private ClientDAO cDao;
    private ProfilDAO profDAO;
    private Map map;
    private Image imgProf;

    public ManageProfil(Map map){
        this.map = map;
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
        this.clientView = new ClientView(profDAO,this.map);
        this.add(this.clientView,BorderLayout.CENTER);
    }
    private JPanel getActionPane()
    {
        JPanel panel = new JPanel();
        JButton btn1 = new JButton("Attribuer Profils");
        JButton btn2 = new JButton("Supprimer profils");
        JButton btn3 = new JButton("generate Purchases");
        JButton btn4  = new JButton("show Course");

        btn1.addActionListener(e -> this.cDao.AttrProf());
        btn2.addActionListener(e -> this.cDao.delAllProfil());
        btn3.addActionListener(e-> this.cDao.attPurchase());
        ArrayList<Integer> listeB = new ArrayList<>();
        listeB.add(new Integer(1));
        listeB.add(new Integer(2));
        listeB.add(new Integer(3));
        listeB.add(new Integer(4));
        listeB.add(new Integer(5));
        btn4.addActionListener(e-> this.map.setCourse(listeB));

        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);

        return panel;
    }
}
