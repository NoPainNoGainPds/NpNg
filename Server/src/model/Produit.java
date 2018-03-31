package model;

import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.ProduitDAO;

/**
 * Represents a product
 */
public class Produit{
    /**
     * Quantity of the product
     */
    private int quantite;
    /**
     * id of the product
     */
    private int id;
    /**
     * weight, length, width of the product
     */
    private float poids,longueur,largeur;
    /**
     * name of the product
     */
    private String nom;
    /**
     * provider of the product
     */
    private Fournisseur fournisseur;
    /**
     * input storage if the product
     */
    private StockEntree stckEntree;
    /**
     * output storage of the product
     */
    private StockSortie stckSortie;
    /**
     * category of the product
     */
    private CategorieProduit catProd;

    /**
     * Constructor
     */
    public Produit()
    {

    }

    /**
     * Constructor
     * @param nom name of the product
     * @param id id of the product
     */
    public Produit(String nom,int id)
    {
        this.id = id;
        this.nom = nom;
    }

    /**
     * Constructor
     * @param id id of the product
     * @param nom name of the product
     * @param quantite quantity of the product
     */
    public Produit(int id,String nom,int quantite)
    {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
    }

    /**
     * get product quantity
     * @return the quantity
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * set the prduct quantity
     * @param quantite the new quantity
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * get the product id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * set the product id
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get the product name
     * @return the name
     */
    public String getNom() {
        return nom;
    }

    /**
     * set the product name
     * @param nom the new name
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * get the product input storage
     * @return the input storage
     */
    public StockEntree getStckEntree() {
        return stckEntree;
    }

    /**
     * set the product input storage
     * @param stckEntree the new input storage
     */
    public void setStckEntree(StockEntree stckEntree) {
        this.stckEntree = stckEntree;
    }

    /**
     * get the product output storage
     * @return the output storage
     */
    public StockSortie getStckSortie() {
        return stckSortie;
    }

    /**
     * set the product output storage
     * @param stckSortie the new output storage
     */
    public void setStckSortie(StockSortie stckSortie) {
        this.stckSortie = stckSortie;
    }

    /**
     *  set the the product provider
     * @param f the new provider
     */
    public void setFournisseurRef(Fournisseur f) {
        this.fournisseur = fournisseur;
    }

    /**
     * get the product provider
     * @return the provider
     */
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    /**
     * get the product weight
     * @return the weight
     */
    public float getPoids() {
        return poids;
    }

    /**
     * set the product weight
     * @param poid the new weight
     */
    public void setPoids(float poid) {
        this.poids = poids;
    }

    /**
     * get the product length
     * @return the length
     */
    public float getLongueur() {
        return longueur;
    }

    /**
     * set the product length
     * @param longueur the new length
     */
    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    /**
     * get the product width
     * @return the width
     */
    public float getLargeur() {
        return largeur;
    }

    /**
     * set the product width
     * @param largeur the new width
     */
    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    /**
     * method to display the product
     * @return the display
     */
    public String toString()
    {
        return ""+this.nom+" qty :"+this.quantite+"";
    }
}
