package utils.daoUtils;

import model.Profil;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProfilDAO {
    private Connection connection;
    public ProfilDAO(Connection connection)
    {
        this.connection = connection;
    }
    public ArrayList<String> getProfilFromClient(int id_client)
    {
        ArrayList<String> liste = new ArrayList<>();
        try
        {
            Statement stmt = this.connection.createStatement();
            String requete = "SELECT pc.nom_profil from client_profil_client cpc join profil_client pc on cpc.id_profil = pc.id_profil where cpc.id_client ="+id_client+";";
            ResultSet res = stmt.executeQuery(requete);
            while(res.next())
            {
                liste.add(res.getString(1));
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return liste;
    }

    /**
     * Method to select all profil(s) in database without course
     * @return Profil list
     */
    public ArrayList<Profil> getProfilsWithoutParcours() {
        ArrayList<Profil> liste = new ArrayList<>();

        try {
            Statement s = this.connection.createStatement();
            String requete = "SELECT * FROM profil_client WHERE id_profil NOT IN (SELECT id_profil FROM parcours)";
            ResultSet r = s.executeQuery(requete);

            while(r.next()) {
                System.out.println("id :" + r.getInt("id_profil"));
                Profil profil = new Profil(r.getString("nom_profil"), r.getInt("id_profil"));
                liste.add(profil);
                System.out.println("profil :" + profil);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(liste.toString());
        return liste;

    }
    //SELECT id_boutique, nom_categorie_boutique FROM boutique as b , categorie_boutique as l WHERE b.id_categorie_boutique = l.id_categorie_boutique

}
