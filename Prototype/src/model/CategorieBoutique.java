package model;

import utils.Constants;
import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.CategorieBoutiqueDAO;

public class CategorieBoutique implements ModelObject {
    private int id;
    private String nom;
    public CategorieBoutique()
    {

    }
    public CategorieBoutique(String nom,int id)
    {
        this.id = id;
        this.nom = nom;
    }
    @Override
    public DAO getDaoClass() {
        return new CategorieBoutiqueDAO();
    }
    @Override
    public String toString()
    {
        return ""+this.nom+ " : "+this.id+";";
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
}
