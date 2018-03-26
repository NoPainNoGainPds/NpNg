//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package model;

import java.util.Date;
import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.StockEntreeDAO;

public class StockEntree {
    private int identree_stock;
    private int id_boutique;
    private Produit id_produit;
    private int quantite;
    private int montant;
    private Date date;

    public StockEntree() {
    }

    public StockEntree(int identree_stock, int id_boutique, Produit id_produit, int quantite, int montant, Date date) {
        this.identree_stock = identree_stock;
        this.id_boutique = id_boutique;
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.montant = montant;
        this.date = date;
    }


    public int getIdentree_stock() {
        return this.identree_stock;
    }

    public void setIdentree_stock(int identree_stock) {
        this.identree_stock = identree_stock;
    }

    public int getId_boutique() {
        return this.id_boutique;
    }

    public void setId_boutique(int id_boutique) {
        this.id_boutique = id_boutique;
    }

    public Produit getProduit() {
        return this.id_produit;
    }

    public void setProduitRef(Produit id_produit) {
        this.id_produit = id_produit;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getMontant() {return this.montant;}

    public void setMontant() {this.montant=montant;}

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return this.quantite + " " + this.montant + " " + this.date;
    }

}
