package model;

import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.EmplacementDAO;
import vue.MyPolygon;

import java.awt.*;
import java.util.ArrayList;

public class Emplacement implements ModelObject{
    private int id;
    private String nom;
    private Boutique reference;
    private int superficie;
    private String cat;
    private ArrayList<Point> path;
    public Emplacement()
    {
        this.nom = "fictif";
        this.id = -1;
    }
    public Emplacement(String nom,int id,int superficie, String cat)
    {
        this.nom = nom;
        this.id=id;
        this.cat = cat;
        this.superficie = superficie;
        this.path = new EmplacementDAO().getPathLocation(this.id);
    }
    public MyPolygon getPolygonsView()
    {
        return new MyPolygon(this.path);
    }
    public String getCat()
    {
        return this.cat;
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
    public DAO getDaoClass() {
        return new EmplacementDAO();
    }
}
