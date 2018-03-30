package model;

import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.CategorieBoutiqueDAO;

/**
 * Represents a store category
 */
public class CategorieBoutique {
    /**
     * id of the category
     */
    private int id;
    /**
     * name of the category
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
     * Methods to display the category
     * @return
     */
    @Override
    public String toString()
    {
        return ""+this.nom+ " : "+this.id+";";
    }

    /**
     * Method to get the id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set the id
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to get the name
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Method to set the name
     * @param nom the new name
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
