package vue;

import model.Client;
import model.Profil;
import utils.Constants;
import utils.daoUtils.ClientDAO;

import javax.swing.*;
import java.util.ArrayList;

public class ManageProfil extends JPanel{
    private ArrayList<Profil> liste;
    private ArrayList<Client> listeClient;
    public ManageProfil(){
        this.liste = new ArrayList<>();
        this.liste.add(new Profil());
        this.add(new JList<Profil>(this.liste.toArray(new Profil[0])));
        ClientDAO cDao = new ClientDAO(Constants.conServ);
        this.listeClient = cDao.findFromReference();
        this.add(new JList<Client>(this.listeClient.toArray(new Client[0])));
    }

}
