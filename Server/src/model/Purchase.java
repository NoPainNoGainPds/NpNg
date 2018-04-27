package model;

/**
 *
 */
public class Purchase {
    private int id;
    private int id_client;
    private int id_Produit;
    private int id_categorie_Boutique;
    public Purchase() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_Produit() {
        return id_Produit;
    }

    public void setId_Produit(int id_Produit) {
        this.id_Produit = id_Produit;
    }

    public int getId_categorie_Boutique() {
        return id_categorie_Boutique;
    }

    public void setId_categorie_Boutique(int id_categorie_Boutique) {
        this.id_categorie_Boutique = id_categorie_Boutique;
    }
    public String toString()
    {
        return "Purchase : "+this.id+" categorie : "+this.id_categorie_Boutique+";";
    }
}
