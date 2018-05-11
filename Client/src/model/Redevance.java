package model;

import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.RedevanceDAO;

import java.util.Date;

/**
 * represents the fee of the stores
 */
public class Redevance implements ModelObject {
    /**
     * id of the fee
     */
    private int id_redevance;
    /**
     * id of the store
     */
    private Boutique id_boutique;
    /**
     * name of the store
     */
    private String nom_boutique;
    /**
     * amount
     */
    private float montant_redevance;
    /**
     * date of the fee
     */
    private Date date_redevance;
    /**
     * number of visitors
     */
    private int freq;
    /**
     * Constructor
     */
    public Redevance() {
    }
    /**
     * Constructor
     * @param id_redevance id
     * @param id_boutique store id
     * @param date_redevance date
     * @param montant_redevance amount
     */
    public Redevance(int id_redevance, Boutique id_boutique, Date date_redevance, float montant_redevance) {
        this.id_redevance = id_redevance;
        this.id_boutique = id_boutique;
        this.date_redevance = date_redevance;
        this.montant_redevance = montant_redevance;
        this.nom_boutique = id_boutique.getNom();
    }

    /**
     * Constructor
     * @param id_redevance id
     * @param id_boutique store id
     * @param date_redevance date
     * @param montant_redevance amount
     * @param freq number of visitors in the store
     */
    public Redevance(int id_redevance, Boutique id_boutique, Date date_redevance, float montant_redevance, int freq) {
        this.id_redevance = id_redevance;
        this.id_boutique = id_boutique;
        this.date_redevance = date_redevance;
        this.montant_redevance = montant_redevance;
        this.nom_boutique = id_boutique.getNom();
        this.freq=freq;
    }

    /**
     * Constructor
     * @param id_redevance id
     */
    public Redevance(int id_redevance) {
        this.id_redevance = id_redevance;
    }
    /**
     * get the fee id
     * @return the id
     */
    public int getid_Redevance() {
        return this.id_redevance;
    }
    /**
     * set the fee id
     * @param id_redevance the new id
     */
    public void setid_Redevance(int id_redevance) {
        this.id_redevance = id_redevance;
    }
    /**
     * get the store id
     * @return the store id
     */
    public Boutique getId_boutique() {
        return this.id_boutique;
    }
    /**
     * set the store id
     * @param id_boutique the new store id
     */
    public void setId_boutique(Boutique id_boutique) {
        this.id_boutique = id_boutique;
    }
    /**
     * get the name of the store
     */
    public String getNom_boutique() {
        return this.nom_boutique;
    }
    /**
     * set the name of the store
     * @param nom_boutique the new name of the store
     */
    public void setNom_boutique(String nom_boutique) {
        this.nom_boutique = nom_boutique;
    }
    /**
     * get the date
     * @return the date
     */
    public Date getDate_redevance() {
        return this.date_redevance;
    }
    /**
     * set the date
     * @param date_redevance the new date
     */
    public void setDate_redevance(Date date_redevance) {
        this.date_redevance = date_redevance;
    }
    /**
     * get the fee
     * @return the fee
     */
    public float getMontant_redevance() {
        return this.montant_redevance;
    }
    /**
     * set the fee
     * @param montant_redevance the new fee
     */
    public void setMontant_redevance(float montant_redevance) {
        this.montant_redevance = montant_redevance;
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
    /**
     * method to display a fee
     * @return the display
     */
    public String toString() {
        return "Boutique: "+this.nom_boutique + "   MONTANT: " + this.montant_redevance+"\u20AC";
    }

    /**
     * get the redevanceDAO object
     * @return the DAO
     */
    public DAO getDaoClass() {
        return new RedevanceDAO();
    }
}
