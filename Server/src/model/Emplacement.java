package model;

import controller.Client;
import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.EmplacementDAO;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a location
 */
public class Emplacement{
    /**
     * Location id
     */
    private int id;
    /**
     * Location name
     */
    private String nom;
    /**
     * The store in the location
     */
    private Boutique reference;
    /**
     * Area of the location
     */
    private int superficie;
    /**
     * Location category
     */
    private String cat;
    /**
     * Path of the location
     */
    private ArrayList<Point> path;

    /**
     * Constructor
     */
    public Emplacement()
    {
        this.nom = "fictif";
        this.id = -1;
    }

    /**
     * Constructor
     * @param nom location name
     * @param id location id
     * @param superficie location area
     * @param cat location category
     */
    public Emplacement(String nom, int id, int superficie, String cat)
    {
        this.nom = nom;
        this.id=id;
        this.cat = cat;
        this.superficie = superficie;
    }

    /**
     * get the location category
     * @return the category
     */
    public String getCat()
    {
        return this.cat;
    }

    /**
     * get location id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set location id
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get location name
     * @return the name
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set location name
     * @param nom the new name
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the store
     * @return the store
     */
    public Boutique getReference() {
        return reference;
    }

    /**
     * get the location area
     * @return the area
     */
    public int getSuperficie()
    {
        return  this.superficie;
    }

    /**
     * Set location area
     * @param superficie the new area
     */
    public void setSuperficie(int superficie)
    {
        this.superficie = superficie;
    }

    /**
     * set location path
     * @param list list of each point
     */
    public void setPath(ArrayList<Point> list)
    {
        this.path = list;
    }

    /**
     * get the location path
     * @return list of each point
     */
    public ArrayList<Point> getPath() {
        return path;
    }

    /**
     * set the store at the location
     * @param reference the store
     */
    public void setReference(Boutique reference) {
        this.reference = reference;
    }

    /**
     * method to display a location
     * @return the display
     */
    public String toString()
    {
        return "Id :"+this.id+" ;" +
                " Nom :"+this.nom+";" +
                " Superficie  : "+this.superficie+"; " +
                "cat : "+this.cat+"; ";
    }

}
