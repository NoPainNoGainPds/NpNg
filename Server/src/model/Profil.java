package model;

public class Profil {
    private String nom;
    private int value;
    public Profil()
    {

    }
    public Profil(String nom, int value)
    {
        this.nom = nom;
        this.value = value;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public String toString()
    {
        return "nom : " + this.getNom() + " id : " + this.getValue();
    }
}
