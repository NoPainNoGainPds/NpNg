//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package model;

import java.util.Date;
/**
 * represents an input storage
 */
public class StockEntree {
    /**
     * id of the input storage
     */
    private int identree_stock;
    /**
     * id of the store
     */
    private int id_boutique;
    /**
     * the product id
     */
    private int id_produit;
    /**
     * the product
     */
    private Produit produit;
    /**
     * quantity
     */
    private int quantite;
    /**
     * amount
     */
    private int montant;
    /**
     * date of the input storage
     */
    private Date date;
    /**
     * Constructor
     */
    public StockEntree() {
    }
    /**
     * Constructor
     * @param identree_stock id
     * @param id_boutique store id
     * @param id_produit product
     * @param quantite quantity
     * @param montant amount
     * @param date date
     */
    public StockEntree(int identree_stock, int id_boutique, int id_produit, int quantite, int montant, Date date) {
        this.identree_stock = identree_stock;
        this.id_boutique = id_boutique;
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.montant = montant;
        this.date = date;
    }

    /**
     * get the input storage id
     * @return the id
     */
    public int getIdentree_stock() {
        return this.identree_stock;
    }
    /**
     * set the input storage id
     * @param identree_stock the new id
     */
    public void setIdentree_stock(int identree_stock) {
        this.identree_stock = identree_stock;
    }
    /**
     * get the store id
     * @return the store id
     */
    public int getId_boutique() {
        return this.id_boutique;
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
    public int getId_produit() {
        return this.id_produit;
    }
    public void setId_produit(int id_produit)
    {
        this.id_produit = id_produit;
    }
    public void setProduit()
    {
        this.produit = produit;
    }
    /**
     * set the product
     * @param produit the new product
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public Produit getProduit() {
        return this.produit;
    }
    /**
     * get the quantity
     * @return the quantity
     */
    public int getQuantite() {
        return this.quantite;
    }
    /**
     * set the quantity
     * @param quantite the new quantity
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    /**
     * get the input storage date
     * @return the date
     */
    public Date getDate() {
        return this.date;
    }
    /**
     * set the input storage date
     * @param date the new date
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * get the amount
     * @return the amount
     */
    public int getMontant() {return this.montant;}
    /**
     * set the amount
     * @param montant the new amount
     */
    public void setMontant(int montant) {this.montant=montant;}
    /**
     * method to display an input storage
     * @return the display
     */

    public String toString() {
        return "QTE:"+this.quantite + "   MONTANT:" + this.montant + "   DATE:" + this.date;
    }

}
