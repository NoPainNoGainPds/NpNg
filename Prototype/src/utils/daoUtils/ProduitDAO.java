package utils.daoUtils;
import org.apache.log4j.Logger;
import model.Produit;
import utils.Constants;
import utils.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ProduitDAO extends DAO<Produit> {

    //Log after any action in the CRUD
    private Logger logger = Logger.getLogger(ProduitDAO.class);
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
            String requete = "UPDATE Produit SET nom_produit=\""+obj.getNom()+"\""+
                    " ,poids="+obj.getPoid()+",longueur="+obj.getLongueur()+",largeur="+obj.getLargeur()+" " +
                    "where id_produit = "+obj.getId()+";";
            stmt.executeUpdate(requete);
            logger.info(requete);
            return true;
        }catch (SQLException e)
        {
            logger.error("SQLException");
            return false;
        }
    }

    @Override
    public Produit find(int id) {
        try
        {
            Statement stmt =  this.connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM PRODUIT WHERE id_produit ="+id+";");
            if(res.first())
            {
                Produit p = new Produit(res.getString("nom_produit"),res.getInt("id_produit"));
                return p;
            }
            logger.info("SELECT * FROM PRODUIT WHERE id_produit ="+id+";");
        } catch (SQLException e) {
            logger.error("SQLException");
        }
        return null;
    }
    @Override
    public ArrayList<Produit> findFromReference(int id)
    {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT es.id_produit,produit.nom_produit, (select sum(quantité) from entrée_stock es2 where es2.id_produit = es.id_produit)-(select sum(quantité) from sortie_stock ss where ss.id_produit = es.id_produit) as quantite "
                    + "FROM entrée_stock es "
                    + "JOIN produit ON es.id_produit = produit.id_produit "
                    + "JOIN sortie_stock ss ON es.id_produit = ss.id_produit "
                    + "WHERE es.id_boutique ="+id+" GROUP BY es.id_produit "
                    + "UNION SELECT es.id_produit,produit.nom_produit, es.quantité "
                    + "FROM entrée_stock es "
                    + "JOIN produit ON es.id_produit = produit.id_produit "
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
            logger.info(requete);
            return listProduit;
        } catch (SQLException e) {
            logger.error("SQLException");
        }
        return null;
    }
    public ArrayList<Produit> findFromReference()
    {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT id_produit,nom_produit FROM Produit ";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Produit> listProduit = new ArrayList<>();

            while(res.next())
            {
                listProduit.add(new Produit(res.getInt("id_produit"),res.getString("nom_produit"),0));
            }
            logger.info(requete);
            return listProduit;
        } catch (SQLException e) {
            logger.error("SQLException");
        }
        return null;
    }

}
