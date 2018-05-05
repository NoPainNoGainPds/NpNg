package utils.daoUtils;

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

}
