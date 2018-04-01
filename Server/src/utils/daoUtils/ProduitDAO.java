package utils.daoUtils;

import controller.Client;
import model.Produit;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class which represents a product. it contains the methods which access the database.
 */
public class ProduitDAO extends DAO<Produit> {

    /**
     * A logger. Use to have a trace of what happen during the execution.
     */
    private Logger logger = Logger.getLogger(ProduitDAO.class);

    /**
     * Constructor.
     */
    public ProduitDAO(Connection con)
    {
        super(con);
    }

    /**
     * Add a new product to the database.
     * @param obj the product to add.
     * @return true if it works, false else.
     */
    @Override
    public boolean create(Produit obj) {
        try
        {
            obj.
            String requete = "INSERT INTO produit (nom_produit,cout_unitaire,quantite,codebarre,largeur,longueur,poids) VALUES ";
            Statement stmt = this.connection.createStatement();
            stmt.executeUpdate(requete);
            logger.info(requete);
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            logger.error(e.toString());
            return false;
        }
    }

    /**
     * Delete a product from the database.
     * @param obj The product to delete.
     * @return true if it works, false else.
     */
    @Override
    public boolean delete(Produit obj) {
        try
        {
            String requete = "DELETE FROM produit where id_produit="+obj.getId()+";";
            Statement stmt = this.connection.createStatement();
            stmt.executeUpdate(requete);
            logger.info(requete);
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            logger.error(e.toString());
            return false;
        }
    }

    /**
     * Update a product from the database.
     * @param obj The product to modify.
     * @return true if it works, false else.
     */
    @Override
    public boolean update(Produit obj) {
        try
        {
            Statement stmt = this.connection.createStatement();
            String requete = "UPDATE Produit SET nom_produit=\""+obj.getNom()+"\""+
                    " ,poids="+obj.getPoids()+",longueur="+obj.getLongueur()+",largeur="+obj.getLargeur()+" " +
                    "where id_produit = "+obj.getId()+";";
            stmt.executeUpdate(requete);
            logger.info(requete);
            return true;
        }catch (SQLException e)
        {
            logger.error(e.toString());
            return false;
        }
    }

    /**
     * Find a product in the database.
     * @param id The product's id.
     * @return the product found.
     */
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
            logger.error(e.toString());
        }
        return null;
    }

    /**
     * Get all the products from a store in the database.
     * @param id The store's id
     * @return A list of the products.
     */
    @Override
    public ArrayList<Produit> findFromReference(int id)
    {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "Select es.id_produit, p.nom_produit, p.cout_unitaire, p.codebarre, p.largeur, p.longueur, p.poids, \n" +
                    "IFNULL ((select sum(es2.quantite) from entree_stock es2 where es2.id_produit = es.id_produit and es2.id_boutique="+id+")-\n" +
                    "(select sum(ss.quantite) from sortie_stock ss where ss.id_produit = es.id_produit and ss.id_boutique="+id+"),  " +
                    "(select sum(es2.quantite) from entree_stock es2 where es2.id_produit = es.id_produit and es2.id_boutique="+id+")) as quantite\n" +
                    "\n" +
                    "FROM entree_stock es, produit p\n" +
                    "where es.id_produit=p.id_produit and es.id_boutique="+id+" GROUP BY es.id_produit";

            ResultSet res = stmt.executeQuery(requete);
            ArrayList<Produit> listProduit = new ArrayList<>();

            while(res.next())
            {
                Produit p = new Produit(res.getInt("id_produit"),res.getString("nom_produit"),res.getInt("quantite"));
                p.setPoids(res.getFloat("poids"));
                p.setLargeur(res.getFloat("largeur"));
                p.setLongueur(res.getFloat("longueur"));
                p.setCout(res.getFloat("cout_unitaire"));
                p.setCodeBarre(res.getInt("codebarre"));
                listProduit.add(p);
            }
            logger.info(requete);
            return listProduit;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }
    /**
     * Get all the products from the database.
     * @return A list of the products.
     */
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
            logger.error(e.toString());
        }
        return null;
    }

}
