package utils.daoUtils;

import model.ClientModel;
import model.Produit;
import model.Purchase;
import utils.DAO;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PurchaseDAO extends DAO<Purchase> {
    public PurchaseDAO(Connection con)
    {
        super(con);
    }
    @Override
    public boolean create(Purchase obj) {
        return false;
    }

    @Override
    public boolean delete(Purchase obj) {
        return false;
    }

    public boolean truncate()
    {
        try{
            String str = "TRUNCATE achat;";
            Statement stmt = this.connection.createStatement();
            stmt.executeUpdate(str);
            stmt.close();
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * lfunction who generate purchases for all clients
     */
    public boolean generatePurchase()
    {

        try {
            ArrayList<ClientModel> listeClient = new ClientDAO(this.connection).findFromReference();
            ArrayList<Produit> listeProduit = new ProduitDAO(this.connection).findFromReference();
            int max_sortieStock = new StockSortieDAO(this.connection).getMaxId();
            for (ClientModel c : listeClient) {
                //for every client generate some random purchase
                int nbPurchase = (int)Math.random()*20;
                System.out.println(c+" nbPurchase : "+nbPurchase);
                for(int i=0;i<nbPurchase;i++)
                {
                    max_sortieStock ++;
                    //add the product to the client
                    int rndProduct = (int)(Math.random()*listeProduit.size())+1;
                    //creation de la sortie de stock
                    int id_boutique = (int)(Math.random()*20)+1;
                    String str = "INSERT INTO `sortie_stock`(`id_sortie`, `quantite`, `date_sortie`, `id_produit`, `id_boutique`) VALUES ("+max_sortieStock+","+1+","+"NOW()"+","+rndProduct+","+id_boutique+")";
                    Statement stmt = this.connection.createStatement();
                    stmt.executeUpdate(str);
                    str = "INSERT INTO `achat`(`id_sortie_stock`, `id_client`) VALUES ("+max_sortieStock+","+c.getId()+");";
                    //insertion du clietn->sotrieStock
                    stmt.executeUpdate(str);
                    stmt.close();
                }
            }
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean update(Purchase obj) {
        return false;
    }

    @Override
    public Purchase find(int id) {
        return null;
    }

    @Override
    public ArrayList<Purchase> findFromReference(int id) {
        try{
            String requete = "SELECT id_Achat,b.id_categorie_boutique,achat.id_client,ss.id_produit from achat JOIN sortie_stock ss ON ss.id_sortie = achat.id_sortie_stock JOIN boutique b on b.id_boutique = ss.id_boutique WHERE achat.id_client ="+id+";";
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Purchase> list = new ArrayList<>();
            while(res.next())
            {
                Purchase p = new Purchase();
                p.setId(res.getInt("id_Achat"));
                p.setId_categorie_Boutique(res.getInt("id_categorie_boutique"));
                p.setId_client(res.getInt("id_client"));
                p.setId_Produit(res.getInt("id_produit"));
                list.add(p);
            }
            stmt.close();
            return list;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Purchase> findFromReference() {
        return null;
    }
}
