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
    private int freq;
    private String renommee;
    private String gamme;
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
        located = false;
    }

    public Boutique(int id, String nom, CategorieBoutique categorie, Emplacement emplacement, String logo)
    {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
        this.emplacement=emplacement;
        located = false;
    }

    /**
     * Constructor of Boutique with store category
     * @param id Store id
     * @param categorieBoutique Store Category
     */
    public Boutique(int id, Emplacement emplacement ,CategorieBoutique  categorieBoutique )
    {
        this.id = id;
        this.emplacement = emplacement;
        this.categorieBoutique = categorieBoutique;
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
        poly.setEmplacement(this.emplacement.getId());
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
    /**
     * get the number of visitors
     * @return the number of visitors
     */
    public int getFreq() {
        return this.freq;
    }
    /**
     * set the number of visitors
     * @param freq the new number of visitors
     */
    public void setFreq(int freq) {
        this.freq = freq;
    }

    public String getRenommee() {
        return renommee;
    }
    public void setRenommee(String renommee) {
        this.renommee = renommee;
    }
    public String getGamme() {
        return gamme;
    }
    public void setGamme(String Gamme) {
        this.gamme = gamme;
    }

    public String toString()
    {
        return "Boutique:"+this.nom+" id boutique :"+this.getId() +"; Emplacement :"+this.emplacement+" Cat :"+this.categorieBoutique;
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
