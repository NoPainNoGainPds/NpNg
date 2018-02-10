package model;

import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.EmplacementDAO;

import java.awt.*;
import java.util.ArrayList;

public class Emplacement implements ModelObject{
    private int id;
    private String nom;
    private Boutique reference;
    private ArrayList<Point> path;
    public Emplacement()
    {
        this.nom = "fictif";
        this.id = -1;
    }
    public Emplacement(String nom,int id)
    {
        this.nom = nom;
        this.id=id;
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

    public Boutique getReference() {
        return reference;
    }

    public void setReference(Boutique reference) {
        this.reference = reference;
    }
    public String toString()
    {
        return ""+this.id+" ; "+this.nom+"";
    }

    @Override
    public Class getDaoClass() {
        return EmplacementDAO.class;
    }
}
