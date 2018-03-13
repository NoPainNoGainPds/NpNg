package model;

import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.StockSortieDAO;

import java.util.Date;

public class StockSortie implements ModelObject {
    private int idsortie_stock;
    private int id_boutique;
    private Produit id_produit;
    private int quantite;
    private Date date;
    public StockSortie()
    {

    }

    public StockSortie(int idsortie_stock, int id_boutique, Produit id_produit, int quantite, Date date) {
        this.idsortie_stock = idsortie_stock;
        this.id_boutique = id_boutique;
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.date = date;
    }

    public int getIdsortie_stock() {
        return idsortie_stock;
    }

    public void setIdsortie_stock(int idsortie_stock) {
        this.idsortie_stock = idsortie_stock;
    }

    public int getId_boutique() {
        return id_boutique;
    }

    public void setId_boutique(int id_boutique) {
        this.id_boutique = id_boutique;
    }

    public Produit getProduit() {
        return id_produit;
    }

    public void setProduitRef(Produit id_produit) {
        this.id_produit = id_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public DAO getDaoClass() {
        return new StockSortieDAO(null);
    }
}
