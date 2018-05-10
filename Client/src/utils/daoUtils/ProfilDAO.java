package utils.daoUtils;


import model.Profile;
import org.apache.log4j.Logger;
import utils.ConnectionServer;

import java.util.ArrayList;


public class ProfilDAO {
    private Logger logger = Logger.getLogger(ProfilDAO.class);

    private ConnectionServer conServ;
    public ProfilDAO(ConnectionServer conserv)
    {
        this.conServ = conserv;
    }
    public ArrayList<String> getProfilClient(int id)
    {
        try {
            String str = "{\"name\":\"GetProfil\",\"id\":"+id+"}\n";
            this.conServ.send(str);
            ArrayList<String> liste = new ArrayList<>();
            boolean recieved = false;
            while(!recieved)
            {
                Object b = (Object)this.conServ.recieve(String.class);
                if (b != null) {
                    //System.out.println("recu!!!!!");
                    String b2 = (String) b;
                    liste.add(b2);
                } else {
                    recieved = true;
                }
            }
            return liste;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean dellAllProfil()
    {
        try{
            String str = "{\"name\":\"DellProfil\",\"id\":"+0+"}\n";
            this.conServ.send(str);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get profils without course in the database
     * @return Profil list
     */
    public ArrayList<Profile> getProfilsWithoutParcours(){
        try{
            String str = "{\"name\":\"SearchProfilsWithoutParcours\",\"id\":"+0+"}\n";
            this.conServ.send(str);
            ArrayList<Profile> liste = new ArrayList<>();
            boolean recieved = false;
            while(!recieved)
            {
                Profile profil = (Profile)this.conServ.recieve(Profile.class);
                if (profil != null) {
                    liste.add(profil);
                } else {
                    recieved = true;
                }
            }
            System.out.println(liste.toString());
            return liste;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
