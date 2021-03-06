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
            String requete = "INSERT INTO parcours(nom_parcours) Values (\""+path.getName()+"\")";
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
                requete = "INSERT INTO parcours_link (id_boutique, id_parcours) VALUES (\"" + store.getId() + "\",\"" + idParcours + "\")";
                //requete = "INSERT INTO `noeud_parcours`(`id_noeud_suivant`, `id_parcours`, `id_emplacement`) VALUES ("+noeudSuivant+","+idParcours+","+store.getEmplacement().getId()+")";
                s.addBatch(requete);
            }
            s.executeBatch();
            for(Boutique b : path.getStoreList())
            {
                System.out.println("id_parcours ="+idParcours+" ; emp :"+b.getEmplacement().getId());
            }
            s.close();



        } catch(SQLException e) {
            e.printStackTrace();
        }
        return idParcours;
    }


    public ArrayList<Integer> emplacementParcours(int id_profil){


        ArrayList<Integer> liste = new ArrayList<>();

        try {
            Statement s = this.connection.createStatement();
            //String requete = "SELECT id_emplacement FROM boutique as b JOIN parcours_link as p on b.id_boutique = p.id_boutique JOIN parcours as a ON a.id_parcours = p.id_parcours JOIN profil_client as pc ON pc.id_parcours = a.id_parcours WHERE pc.id_profil = "+id_profil;
            String requete = "SELECT b.id_emplacement FROM boutique b Join parcours_link p ON p.id_boutique=b.id_boutique JOIN profil_client pr ON pr.id_parcours = p.id_parcours Where pr.id_profil = "+id_profil;
            ResultSet r = s.executeQuery(requete);
            System.out.println("Profil : "+id_profil);
            while(r.next()) {
                System.out.println("Emplacement : "+r.getInt(1));
                liste.add(r.getInt(1));
                //System.out.println("Emplacement :" + e);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(liste.toString());
        return liste;

    }
    //public void deleteAll(){

       // String requete = "Update profil_client SET id_parcours= null";

    //}
}
