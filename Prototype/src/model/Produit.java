package model;

import utils.Constants;
import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.ProduitDAO;

public class Produit implements ModelObject{
    private int quantite;
    private int id;
    private float poids,longueur,largeur,cost;
    private String nom, barcode;
    private Fournisseur fournisseur;
    private StockEntree stckEntree;
    private StockSortie stckSortie;
    private CategorieProduit catProd;

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

    public void setFournisseurRef(Fournisseur f) {
        this.fournisseur = fournisseur;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getLongueur() {
        return longueur;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setBarcode(String barcode)
    {
        this.barcode = barcode;
    }

    public String getBarCode()
    {
        return this.barcode;
    }

    public String toString()
    {
        return ""+this.nom+" qty :"+this.quantite+"";
    }
    @Override
    public DAO getDaoClass() {
        return new ProduitDAO(Constants.conServ);
    }
}
