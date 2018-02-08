package model;

public class Produit {
    private int quantite;
    private int id;
    private String nom;
    public Produit()
    {

    }
    public Produit(String nom,int id, int quantite)
    {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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
}
