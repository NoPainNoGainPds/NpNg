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
    private Map map;
    public ClientView(ProfilDAO profDAO,Map map)
    {
        super();
        this.map = map;
        this.profDAO = profDAO;
        this.setLayout(new MigLayout("wrap","[grow]","[][fill]"));
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
        this.add(this.iconProfil,"cell 0 0 ");
        this.add(this.nom,"cell 2 0");
        this.add(this.prenom,"cell 1 0");
        this.add(this.age,"cell  0 1");
        this.add(this.mail,"cell 1 1");
        this.add(this.telephone,"cell 2 1");
        this.add(this.adrs,"cell 0 2");
        this.add(this.profils,"cell 1 2");
        this.add(this.map,"cell 0 3 4 4, grow, push");
        //ColorCube3D cc3d = new ColorCube3D();
        //cc3d.setMinimumSize(new Dimension(45,45));
        //this.add(cc3d);
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
