package R3;

import controller.Client;
import model.CategorieBoutique;
import model.Emplacement;
import model.Produit;

import java.util.ArrayList;

public class MarkedStore {
    private String name;
    private int id;
    private Emplacement location;
    private ArrayList<Produit> productsList;
    private CategorieBoutique storeCategory;
    private String logo;
    private int mark;

    public MarkedStore(Client c, int id, String name, int category, int location, String logo, int mark) {
        this.name = name;
        this.id = id;
        this.logo = logo;
        this.location = c.geteDAO().find(location);
        this.location.setPath(c.geteDAO().getPathLocation(location));
        this.storeCategory = c.getCbDAO().find(category);
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Emplacement getLocation() {
        return location;
    }

    public void setLocation(Emplacement location) {
        this.location = location;
    }

    public ArrayList<Produit> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Produit> productsList) {
        this.productsList = productsList;
    }

    public CategorieBoutique getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(CategorieBoutique storeCategory) {
        this.storeCategory = storeCategory;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
