package model;

import utils.ModelObject;
import utils.daoUtils.ProduitDAO;

public class Produit implements ModelObject{
    private int quantite;
    private int id;
    private String nom;
    private StockEntree stckEntree;
    private StockSortie stckSortie;
    public Produit()
    {

    }
    public Produit(String nom,int id)
    {
        this.id = id;
        this.nom = nom;
    }
    public Produit(int id,String nom,int quantite)
    {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
    }
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public StockEntree getStckEntree() {
        return stckEntree;
    }

    public void setStckEntree(StockEntree stckEntree) {
        this.stckEntree = stckEntree;
    }

    public StockSortie getStckSortie() {
        return stckSortie;
    }

    public void setStckSortie(StockSortie stckSortie) {
        this.stckSortie = stckSortie;
    }
    public String toString()
    {
        return "Produit:"+this.nom+" quantite :"+this.quantite+"";
    }
    @Override
    public Class getDaoClass() {
        return ProduitDAO.class;
    }
}
