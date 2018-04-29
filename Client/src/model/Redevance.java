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
    private int id_boutique;
    /**
     * amount
     */
    private int montant_redevance;
    /**
     * date of the payment
     */
    private Date date_redevance;
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
    public Redevance(int id_redevance, int id_boutique, Date date_redevance, int montant_redevance) {
        this.id_redevance = id_redevance;
        this.id_boutique = id_boutique;
        this.date_redevance = date_redevance;
        this.montant_redevance = montant_redevance;
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
    public int getId_boutique() {
        return this.id_boutique;
    }
    /**
     * set the store id
     * @param id_boutique the new store id
     */
    public void setId_boutique(int id_boutique) {
        this.id_boutique = id_boutique;
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
    public int getMontant_redevance() {
        return this.montant_redevance;
    }
    /**
     * set the fee
     * @param montant_redevance the new fee
     */
    public void setMontant_redevance(int montant_redevance) {
        this.montant_redevance = montant_redevance;
    }

    /**
     * method to display a fee
     * @return the display
     */
    public String toString() {
        return "Boutique:"+this.id_boutique + "   MONTANT:" + this.montant_redevance + "   DATE:" + this.date_redevance;
    }

    /**
     * get the redevanceDAO object
     * @return the DAO
     */
    public DAO getDaoClass() {
        return new RedevanceDAO();
    }
}
