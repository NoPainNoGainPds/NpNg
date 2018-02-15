package model;

import utils.Constants;
import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.CategorieBoutiqueDAO;
import utils.daoUtils.EmplacementDAO;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Boutique implements ModelObject {
    private String nom;
    private int id;
    private Emplacement emplacement;
    private ArrayList<Produit> listeProduit;
    private CategorieBoutique categorieBoutique;
    public Boutique()
    {

    }
    public Boutique(int id,String nom,int categorie,int emplacement)
    {
        this.id = id;
        this.nom = nom;
        EmplacementDAO eDAO = new EmplacementDAO();
        this.emplacement = eDAO.find(emplacement);
        CategorieBoutiqueDAO cbDAO = new CategorieBoutiqueDAO();
        this.categorieBoutique = cbDAO.find(categorie);
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
        return "Boutique:"+this.nom+"; Emplacement :"+this.emplacement;
    }
    public DAO getDaoClass()
    {
        return new BoutiqueDAO();
    }

    public CategorieBoutique getCategorieBoutique() {
        return categorieBoutique;
    }

    public void setCategorieBoutiqueRef(CategorieBoutique categorieBoutique) {
        this.categorieBoutique = categorieBoutique;
    }
}
