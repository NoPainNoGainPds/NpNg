package vue;

import model.Client;
import model.Profil;
import utils.Constants;
import utils.daoUtils.ClientDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class ManageProfil extends JPanel{
    private ArrayList<Profil> liste;
    private ArrayList<Client> listeClient;
    public ManageProfil(){
        this.setLayout(new BorderLayout());
        ClientDAO cDao = new ClientDAO(Constants.conServ);
        System.out.println("demande client");
        this.listeClient = cDao.findFromReference();
        System.out.println("ici client");
        JList<Client> listeClient = new JList<Client>(this.listeClient.toArray(new Client[0]));
        JScrollPane pane = new JScrollPane(listeClient);
        this.add(pane, BorderLayout.WEST);

        listeClient.addListSelectionListener((ListSelectionEvent listSelectionEvent) ->
            {
                //au clique, afficher les achats du client.
                Client c = listeClient.getSelectedValue();
            }
        );

    }

}
