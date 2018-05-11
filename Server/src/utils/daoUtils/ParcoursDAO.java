package utils.daoUtils;

import model.Boutique;
import model.CategorieBoutique;
import model.Emplacement;
import model.Parcours;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParcoursDAO {

    private Connection connection;

    public ParcoursDAO(Connection connection)
    {
        this.connection = connection;
    }


    public int create(Parcours path) {

        int idParcours = 0;
        try {
            //Create new path
            Statement s = this.connection.createStatement();
            String requete = "INSERT INTO parcours(nom_parcours) Values ("+path.getName()+")";
            s.executeUpdate(requete, Statement.RETURN_GENERATED_KEYS);

            //Get id of the created path
            ResultSet rs = s.getGeneratedKeys();

            if (rs.next()) {
                idParcours = rs.getInt(1);
            }
            rs.close();

            //Iterate through all the stores in the list and insert into the link table
            s = this.connection.createStatement();
            for (Boutique store : path.getStoreList()) {
                requete = "INSERT INTO parcours_link (id_boutique, id_parcours) VALUES (" + store.getId() + "," + idParcours + ")";
                s.addBatch(requete);
            }
            s.executeBatch();



        } catch(SQLException e) {
            e.printStackTrace();
        }
        return idParcours;
    }



    //public void deleteAll(){

       // String requete = "Update profil_client SET id_parcours= null";

    //}
}
