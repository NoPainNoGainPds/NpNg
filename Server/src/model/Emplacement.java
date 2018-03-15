package model;

import controller.Client;
import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.EmplacementDAO;

import java.awt.*;
import java.util.ArrayList;

public class Emplacement{
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
    public Emplacement(String nom, int id, int superficie, String cat)
    {
        this.nom = nom;
        this.id=id;
        this.cat = cat;
        this.superficie = superficie;
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

    public int getSuperficie()
    {
        return  this.superficie;
    }
    public void setSuperficie(int superficie)
    {
        this.superficie = superficie;
    }
    public void setPath(ArrayList<Point> list)
    {
        this.path = list;
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    public void setReference(Boutique reference) {
        this.reference = reference;
    }
    public String toString()
    {
        return "Id :"+this.id+" ;" +
                " Nom :"+this.nom+";" +
                " Superficie  : "+this.superficie+"; " +
                "cat : "+this.cat+"; ";
    }

}
