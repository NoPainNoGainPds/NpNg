package model;

public class Boutique {
    private String nom;
    private int id;
    private Emplacement emplacement;
    public Boutique()
    {

    }
    public Boutique(int id,String nom)
    {
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Emplacement getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }
    public String toString()
    {
        return "Boutique:"+this.nom+"";
    }
}
