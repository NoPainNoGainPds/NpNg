package model;

public class Emplacement {
    private int id;
    private String nom;
    private Boutique reference;
    public Emplacement(String nom,int id)
    {
        this.nom = nom;
        this.id=id;
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

    public void setReference(Boutique reference) {
        this.reference = reference;
    }
}
