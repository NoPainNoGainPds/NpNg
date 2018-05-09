package model;

public class CauseSortieStock {
    private int id_cause;

    private String nom_cause;

    public CauseSortieStock(int id, String nom){
        this.id_cause = id;
        this.nom_cause = nom;
    }

    public void setId_cause(int id_cause) {
        this.id_cause = id_cause;
    }

    public int getId_cause() {
        return id_cause;
    }

    public void setNom_cause(String nom_cause) {
        this.nom_cause = nom_cause;
    }

    public String getNom_cause() {
        return nom_cause;
    }
}
