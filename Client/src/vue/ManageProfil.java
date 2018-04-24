package vue;

import model.Client;
import model.Profil;

import javax.swing.*;
import java.util.ArrayList;

public class ManageProfil extends JPanel{
    private ArrayList<Profil> liste;
    private ArrayList<Client> listeClient;
    public ManageProfil(){
        this.liste = new ArrayList<>();
        this.liste.add(new Profil());
        this.add(new JList<Profil>(this.liste.toArray(new Profil[0])));
        this.listeClient = new ArrayList<>();
        this.listeClient.add(new Client());
        this.add(new JList<Client>(this.listeClient.toArray(new Client[0])));
    }

}
