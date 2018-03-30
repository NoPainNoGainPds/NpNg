package model;

import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.StockSortieDAO;

import java.util.Date;

/**
 * represents an output storage
 */
public class StockSortie  {
    /**
     * output storage id
     */
    private int idsortie_stock;
    /**
     * store id
     */
    private int id_boutique;
    /**
     * the product
     */
    private Produit id_produit;
    /**
     * the quantity
     */
    private int quantite;
    /**
     * the output storage date
     */
    private Date date;

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
     * @param id_produit product
     * @param quantite quantity
     * @param date date
     */
    public StockSortie(int idsortie_stock, int id_boutique, Produit id_produit, int quantite, Date date) {
        this.idsortie_stock = idsortie_stock;
        this.id_boutique = id_boutique;
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.date = date;
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
     * get the product
     * @return the product
     */
    public Produit getProduit() {
        return id_produit;
    }

    /**
     * set the product
     * @param id_produit the new product
     */
    public void setProduitRef(Produit id_produit) {
        this.id_produit = id_produit;
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

}
