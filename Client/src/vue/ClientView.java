package vue;

import model.Client;
import net.miginfocom.swing.MigLayout;
import utils.daoUtils.ProfilDAO;

import javax.swing.*;
import java.awt.*;

public class ClientView extends JPanel {
    private Client client;
    private Image img;
    private ImageComponent iconProfil;
    private JLabel nom,prenom,age,mail,telephone,adrs,profils;
    private ProfilDAO profDAO;
    public ClientView(ProfilDAO profDAO)
    {
        super();
        this.profDAO = profDAO;
        this.setLayout(new MigLayout("wrap","[grow]","[][grow]"));
        this.nom = new JLabel("Nom :");
        this.prenom = new JLabel("Prenom :");
        this.age = new JLabel("Age :");
        this.mail = new JLabel("Mail :");
        this.telephone = new JLabel("Telephone :");
        this.adrs = new JLabel("Address :");
        this.profils = new JLabel("Profils :");
        this.img = Toolkit.getDefaultToolkit().getImage("Client/src/res/Profil.png");
        this.iconProfil = new ImageComponent(this.img,true);
        this.iconProfil.setMinimumSize(new Dimension(75,75));
        this.iconProfil.setImageDimention(75,74);
        this.add(this.iconProfil,"cell 0 0");
        this.add(this.nom,"cell 1 0");
        this.add(this.prenom,"");
        this.add(this.age);
        this.add(this.mail);
        this.add(this.telephone);
        this.add(this.adrs);
        this.add(this.profils);
    }
    public void setClient(Client c)
    {
        this.client = c;
        this.nom.setText("Nom : "+c.getNom());
        this.prenom.setText("Prenom : "+c.getPrenom());
        this.age.setText("Age : "+c.Age());
        this.mail.setText("Mail : "+c.getMail());
        this.telephone.setText("Telephone : "+c.getPhone());
        this.adrs.setText("Address : "+c.getAddr());
        String res = "";
        for(String s : this.profDAO.getProfilClient(this.client.getId()))
        {
            res+=(s+";");
        }
        this.profils.setText("Profils :"+res);
    }
}
