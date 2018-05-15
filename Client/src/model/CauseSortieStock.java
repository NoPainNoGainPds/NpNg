package model;

import utils.DAO;
import utils.ModelObject;

public class CauseSortieStock{
     private int id_cause_sortie_stock;

     private String nom_cause;
    public CauseSortieStock()
    {

    }
     public CauseSortieStock(String s){
         this.nom_cause = s;
     }

     public CauseSortieStock(int id, String nom){
         this.id_cause_sortie_stock = id;
         this.nom_cause = nom;
     }

    public void setId_cause_sortie_stock(int id_cause_sortie_stock) {
        this.id_cause_sortie_stock = id_cause_sortie_stock;
    }

    public int getId_cause_sortie_stock() {
        return id_cause_sortie_stock;
    }

    public void setNom_cause(String nom_cause) {
        this.nom_cause = nom_cause;
    }

    public String getNom_cause() {
        return nom_cause;
    }

    @Override
    public String toString() {
        return "Cause : "+this.nom_cause+" ID : "+this.id_cause_sortie_stock;
    }

}
