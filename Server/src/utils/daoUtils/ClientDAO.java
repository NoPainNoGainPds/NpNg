package utils.daoUtils;

import model.ClientModel;
import model.Purchase;
import utils.Constants;
import utils.DAO;

import java.sql.*;
import java.util.ArrayList;

public class ClientDAO extends DAO<ClientModel> {
    public ClientDAO(Connection con)
    {
        super(con);
    }
    @Override
    public boolean create(ClientModel obj) {
        return false;
    }

    @Override
    public boolean delete(ClientModel obj) {
        return false;
    }

    @Override
    public boolean update(ClientModel obj) {
        return false;
    }

    @Override
    public ClientModel find(int id) {
        return null;
    }

    @Override
    public ArrayList<ClientModel> findFromReference(int id) {
        return null;
    }

    @Override
    public ArrayList<ClientModel> findFromReference() {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT `id_client`, `nom_client`, `prenom_client`, `sexe`, `adresse`, `date_naissance`, `telephone`, `mail` FROM `client`";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<ClientModel> listBoutique = new ArrayList<>();
            while(res.next())
            {
                listBoutique.add(new ClientModel(res.getInt("id_client"),res.getString("nom_client"),res.getString("prenom_client"),res.getString("sexe"),res.getString("adresse"),res.getString("mail"),res.getString("telephone"),res.getString("date_naissance"),"card"));
            }
            stmt.close();
            return listBoutique;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean delAllProfil()
    {
        try
        {
            Statement stmt = this.connection.createStatement();
            String requete ="";
            ResultSet res = stmt.executeQuery(requete);
            stmt.close();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    /**
     *
     */
    public boolean attrProf()
    {
        PurchaseDAO pDAO = new PurchaseDAO(this.connection);
        ArrayList<ClientModel> liste = this.findFromReference();
        //for every clients
        for(ClientModel client : liste)
        {
            int nb_sport =0,nb_mobi = 0,nb_tech = 0,nb_alim = 0,nb_educ = 0;
            ArrayList<Purchase> purchases = pDAO.findFromReference(client.getId());
            for(Purchase p : purchases)
            {
                System.out.println(p);
                switch(p.getId_categorie_Boutique())
                {
                    case Constants.CAT_SPORT :
                        nb_sport++;
                        break;
                    case Constants.CAT_MOBILIER:
                        nb_mobi++;
                        break;
                    case Constants.CAT_ALIMENTAIRE:
                        nb_alim++;
                        break;
                    case Constants.CAT_EDUCATION:
                        nb_educ++;
                        break;
                    case Constants.CAT_TECH:
                        nb_tech++;
                        break;
                }
                //calcul de pourcentage
                int tot = nb_alim+nb_educ+nb_mobi+nb_sport+nb_tech;

            }
        }
        return false;
    }
}
