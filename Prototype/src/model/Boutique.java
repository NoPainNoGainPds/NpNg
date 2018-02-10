package model;

import utils.Constants;
import utils.ModelObject;
import utils.daoUtils.BoutiqueDAO;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Boutique implements ModelObject {
    private String nom;
    private int id;
    private Emplacement emplacement;
    private ArrayList<Produit> listeProduit;
    public Boutique()
    {

    }
    public Boutique(int id,String nom)
    {
        this.id = id;
        this.nom = nom;
    }
    public void populateListProduit()
    {

    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        return "Boutique:"+this.nom+"";
    }
    public Class getDaoClass()
    {
        return BoutiqueDAO.class;
    }
}
