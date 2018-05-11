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
            String requete ="TRUNCATE client_profil_client;";
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
            ArrayList<Purchase> purchases = pDAO.findFromReference(client.getId());
            int nb_sport =0,nb_mobi = 0,nb_tech = 0,nb_alim = 0,nb_educ = 0,nb_mode=0,none=0;
            //System.out.println("CLient : "+client+" : nb Achat : "+purchases.size());
            for(Purchase p : purchases)
            {
                //System.out.println(p);
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
                    case Constants.CAT_MODE:
                        nb_mode++;
                        break;
                    default:
                        none++;
                        break;
                }

                //calcul de pourcentage
            }
            float tot = nb_sport+nb_alim+nb_educ+nb_mobi+nb_mode+nb_tech+none;
            if(tot!=0) {
                float[] array = new float[6];
                array[0]= ((float)nb_sport / tot) * 100f;
                array[1] = ((float)nb_mobi / tot) * 100f;
                array[2] = ((float)nb_tech / tot) * 100f;
                array[3] = ((float)nb_alim / tot) * 100f;
                array[4] = ((float)nb_educ / tot) * 100f;
                array[5] = ((float)nb_mode/tot)*100f;
                iAprofils(array,client);
            }
        }
        return false;
    }
    /**
     * Methode that chossing all profils for the  client
     * @param array
     * @param client
     */
    private void iAprofils(float[] array,ClientModel client)
    {
        int prof = 0;
        Statement stmt = null;
        try {
            String req1 = "Delete from client_profil_client where id_client ="+client.getId()+";";
            stmt = this.connection.createStatement();
            stmt.executeUpdate(req1);

        for(int i=0;i<array.length;i++)
        {
                float value = array[i];
                prof = i+1;
                if(value <10.0f)
                {
                    prof=-1;
                }
                else if(value < 25.0f)
                {
                    //pas vraiment le profil
                    prof = prof;
                }
                else if(value >= 25.0f && value < 50.0f)
                {
                    //achete souvent
                    prof+=100;
                }else if(value >= 50.0f && value < 75.0f)
                {
                    //aime beaucoup
                    prof+=200;
                }else if(value > 75.0f)
                {
                    //Profil++
                    prof+=300;
                }
                if(prof!=-1) {

                    String req = "INSERT INTO client_profil_client  (id_client,id_profil) values(" + client.getId() + "," + prof + ");";
                    stmt.executeUpdate(req);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getFirstProfil(int idClient)
    {
        String str = "SELECET MAX(id_profil) from client_profil_client where id_client ="+idClient+";";
        try
        {
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery(str);
            int retour = -1;
            while(res.next())
            {
                retour = res.getInt(1);
            }
            return retour;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
