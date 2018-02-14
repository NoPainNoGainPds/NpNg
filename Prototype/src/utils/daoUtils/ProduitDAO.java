package utils.daoUtils;

import model.Produit;
import utils.Constants;
import utils.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ProduitDAO extends DAO<Produit> {
    public ProduitDAO()
    {
        super(Constants.DB.getConnection());
    }
    @Override
    public boolean create(Produit obj) {
        return false;
    }

    @Override
    public boolean delete(Produit obj) {
        return false;
    }

    @Override
    public boolean update(Produit obj) {
        try
        {
            Statement stmt = Constants.DB.getConnection().createStatement();
            String requete = "UPDATE Produit SET nom=\""+obj.getNom()+"\""+
                    " ,poid="+obj.getPoid()+",longueur="+obj.getLongueur()+",largeur="+obj.getLargeur()+" " +
                    "where idProduit = "+obj.getId()+";";
            stmt.executeUpdate(requete);
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Produit find(int id) {
        try
        {
            Statement stmt =  this.connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM PRODUIT WHERE idProduit ="+id+";");
            if(res.first())
            {
                Produit p = new Produit(res.getString("nom"),res.getInt("idProduit"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<Produit> findFromReference(int id)
    {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT es.id_produit,produit.nom, (select sum(quantité) from entrée_stock es2 where es2.id_produit = es.id_produit)-(select sum(quantité) from sortie_stock ss where ss.id_produit = es.id_produit) as quantite "
                    + "FROM entrée_stock es "
                    + "JOIN produit ON es.id_produit = produit.iDProduit "
                    + "JOIN sortie_stock ss ON es.id_produit = ss.id_produit "
                    + "WHERE es.id_boutique ="+id+" GROUP BY es.id_produit "
                    + "UNION SELECT es.id_produit,produit.nom, es.quantité "
                    + "FROM entrée_stock es "
                    + "JOIN produit ON es.id_produit = produit.iDProduit "
                    + "WHERE es.id_boutique = "+id+" "
                    + "AND es.id_produit not in ("
                    + "		SELECT id_produit from sortie_stock"
                    + "		);";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Produit> listProduit = new ArrayList<>();

            while(res.next())
            {
                listProduit.add(new Produit(res.getInt("id_produit"),res.getString("nom"),res.getInt("quantite")));
            }
            return listProduit;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Produit> findFromReference()
    {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT idProduit,nom FROM Produit ";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Produit> listProduit = new ArrayList<>();

            while(res.next())
            {
                listProduit.add(new Produit(res.getInt("idProduit"),res.getString("nom"),0));
            }
            return listProduit;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
