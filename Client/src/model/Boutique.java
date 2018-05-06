package model;

import utils.Constants;
import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.CategorieBoutiqueDAO;
import utils.daoUtils.EmplacementDAO;
import vue.MyPolygon;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * represent Boutique Object with name, place and categorie.
 */
public class Boutique implements ModelObject {
    private String nom;
    private int id;
    private Emplacement emplacement;
    private ArrayList<Produit> listeProduit;
    private CategorieBoutique categorieBoutique;
    private String logo;
    private boolean located;
    public Boutique()
    {

    }

    /**
     * Constructor of Boutique
     * @param id id of the store
     * @param nom name of the store
     * @param categorie categorie of the store
     * @param emplacement location of the store
     */
    public Boutique(int id,String nom,int categorie,int emplacement,String logo)
    {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
    }

    public Boutique(int id, String nom, int categorie, Emplacement emplacement, String logo)
    {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
        this.emplacement=emplacement;
        located = false;
    }
    /**
     *
     * @return polygon of this store
     */
    public MyPolygon getPolygonsView()
    {

        MyPolygon poly = this.emplacement.getPolygonsView();
        poly.setBoutique(this);
        return poly;
    }

    /**
     *
     * @return Name of the store
     */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Emplacement getEmplacement() {
        return emplacement;
    }
    public void setEmplacement(Emplacement emplacement) { this.emplacement=emplacement;}

    public void setEmplacementRef(Emplacement emplacement) {
        this.emplacement = emplacement;
    }

    public ArrayList<Produit> getListeProduit() {
        return listeProduit;
    }

    public void setListeProduit(ArrayList<Produit> listProduit)
    {
        this.listeProduit = listProduit;
    }
    public String toString()
    {
        return "Boutique:"+this.nom+"; Emplacement :"+this.emplacement+" Cat :"+this.categorieBoutique;
    }
    public DAO getDaoClass()
    {
        return null;
    }

    public CategorieBoutique getCategorieBoutique() {
        return categorieBoutique;
    }

    public void setCategorieBoutiqueRef(CategorieBoutique categorieBoutique) {
        this.categorieBoutique = categorieBoutique;
    }
    public void setLocated(boolean bool) {
        located = bool;
    }
    public boolean getLocated() {
        return located;
    }
}
