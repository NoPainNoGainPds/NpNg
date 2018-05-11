package model;

import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.StockSortieDAO;

import java.sql.SQLException;
import java.util.Date;

public class StockSortie {
    /**
     * output storage id
     */
    private int idsortie_stock;
    /**
     * store id
     */
    private int id_boutique;
    /**
     * product id
     */
    private int id_produit;
    /**
     * the product
     */
    private Produit produit;
    /**
     * the quantity
     */
    private int quantite;
    /**
     * the output storage date
     */
    private Date date;
    /**
     * cause id
     */
    private int id_cause;
    /**
     * Constructor
     */
    public StockSortie()
    {

    }


    /**
     * Constructor
     * @param idsortie_stock output storage id
     * @param id_boutique store id
     * @param id_produit product id
     * @param produit product
     * @param quantite quantity
     * @param date date
     * @param id_cause cause id
     */
    public StockSortie(int idsortie_stock, int id_boutique,int id_produit,Produit produit, int quantite, Date date, int id_cause) {
        this.idsortie_stock = idsortie_stock;
        this.id_boutique = id_boutique;
        this.id_produit = id_produit;
        this.produit = produit;
        this.quantite = quantite;
        this.date = date;
        this.id_cause = id_cause;
    }

    /**
     * get the output storage id
     * @return the id
     */
    public int getIdsortie_stock() {
        return idsortie_stock;
    }

    /**
     * set the output storage id
     * @param idsortie_stock the new id
     */
    public void setIdsortie_stock(int idsortie_stock) {
        this.idsortie_stock = idsortie_stock;
    }
    /**
     * get the store id
     * @return the store id
     */
    public int getId_boutique() {
        return id_boutique;
    }
    /**
     * set the store id
     * @param id_boutique the new store id
     */
    public void setId_boutique(int id_boutique) {
        this.id_boutique = id_boutique;
    }
    /**
     * get the product id
     * @return the store id
     */
    public int getId_produit() {
        return id_produit;
    }
    /**
     * set the produit id
     * @param id_produit the new store id
     */
    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }
    /**
     * get the product
     * @return the product
     */
    public Produit getProduit() {
        return produit;
    }
    /**
     * set the product
     * @param produit the new product
     */
    public void setProduitRef(Produit produit) {
        this.produit = produit;
    }
    /**
     * get the quantity
     * @return the quantity
     */
    public int getQuantite() {
        return quantite;
    }
    /**
     * set the quantity
     * @param quantite the new quantity
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    /**
     * get the output storage date
     * @return the date
     */
    public Date getDate() {
        return date;
    }
    /**
     * set the output storage date
     * @param date the new output storage date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_cause() {return id_cause; }

    public void setId_cause(int id_cause) {this.id_cause = id_cause;}

    public String toString()
    {
        return "QTE:"+this.quantite+"  DATE:"+this.date;
    }

}
