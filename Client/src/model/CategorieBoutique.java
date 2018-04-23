package model;

import utils.Constants;
import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.CategorieBoutiqueDAO;

/**
 * Represents a store category
 */
public class CategorieBoutique implements ModelObject {
    /**
     * id of the category
     */
    private int id;
    /**
     * name of the catgeory
     */
    private String nom;

    /**
     * Constructor
     */
    public CategorieBoutique()
    {

    }

    /**
     * Constructor
     * @param nom name of the category
     * @param id id of the category
     */
    public CategorieBoutique(String nom,int id)
    {
        this.id = id;
        this.nom = nom;
    }

    /**
     * get the CategorieBoutiqueDAO
     * @return the DAO object
     */
    @Override
    public DAO getDaoClass() {
        return new CategorieBoutiqueDAO();
    }

    /**
     * Method to display a store category
     * @return
     */
    @Override
    public String toString()
    {
        return ""+this.nom+ " : "+this.id+";";
    }

    /**
     * get the store category id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * set the id of the store category
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get the name of the store category
     * @return the name
     */
    public String getNom() {
        return nom;
    }

    /**
     * set the name of the category
     * @param nom the new name
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
