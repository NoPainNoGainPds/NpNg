package model;


import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.BonLivraisonDAO;
import java.util.Date;

public class BonLivraison {

    private int id_bonlivraison;

    private int id_fournisseur;

    private float montant_livraison;

    private Date date_livraison;

    public BonLivraison(){
    }

    public BonLivraison(int id_fournisseur, float montant, Date date_livraison){
        this.id_fournisseur = id_fournisseur;
        this.montant_livraison = montant;
        this.date_livraison = date_livraison;
    }

    public void setId_bonlivraison(int id_bonlivraison){
        this.id_bonlivraison = id_bonlivraison;
    }

    public int getId_bonlivraison(){
        return this.id_bonlivraison;
    }

    public void setId_fournisseur(int id_fournisseur){
        this.id_fournisseur = id_fournisseur;
    }

    public int getId_fournisseur(){
        return this.id_fournisseur;
    }

    public void setMontant_livraison(float montant){
        this.montant_livraison = montant;
    }

    public float getMontant_livraison(){
        return this.montant_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

}
