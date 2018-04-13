package R3;

import controller.Client;
import model.Boutique;
import model.CategorieBoutique;
import model.Emplacement;
import model.Produit;

import java.util.ArrayList;

/**
 * Represents a store with a mark
 */
public class MarkedStore {
    /**
     * Name of the store
     */
    private String name;
    /**
     * Id of the store
     */
    private int id;
    /**
     * Location of the store
     */
    private Emplacement location;
    /**
     * List of the products of the store
     */
    private ArrayList<Produit> productsList;
    /**
     * Category of the store
     */
    private CategorieBoutique storeCategory;
    /**
     * Logo of the store
     */
    private String logo;
    /**
     * The store
     */
    private Boutique store;
    /**
     * Mark of the store
     */
    private int mark;

    /**
     * Constructor
     * @param c the client to get the connection
     * @param id id of the store
     * @param name name of the store
     * @param category category of the store
     * @param location location of the store
     * @param logo logo of the store
     * @param mark mark of the store
     * @param store the store
     */
    public MarkedStore(Client c, int id, String name, int category, int location, String logo, Boutique store, int mark) {
        this.name = name;
        this.id = id;
        this.logo = logo;
        this.location = c.geteDAO().find(location);
        this.location.setPath(c.geteDAO().getPathLocation(location));
        this.storeCategory = c.getCbDAO().find(category);
        this.store = store;
        this.mark = mark;
    }

    /**
     * Get the name of the store
     * @return the name of the store
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the store
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the id of the store
     * @return the id of the store
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the store
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the location of the store
     * @return the location of the store
     */
    public Emplacement getLocation() {
        return location;
    }

    /**
     * Set the location of the store
     * @param location the new location
     */
    public void setLocation(Emplacement location) {
        this.location = location;
    }

    /**
     * Get the list of the products of the store
     * @return the products'list of the store
     */
    public ArrayList<Produit> getProductsList() {
        return productsList;
    }

    /**
     * Set the produts'list of the store
     * @param productsList the new products'list
     */
    public void setProductsList(ArrayList<Produit> productsList) {
        this.productsList = productsList;
    }

    /**
     * Get the category of the store
     * @return the category of the store
     */
    public CategorieBoutique getStoreCategory() {
        return storeCategory;
    }

    /**
     * Set the category of the store
     * @param storeCategory the new category of te store
     */
    public void setStoreCategory(CategorieBoutique storeCategory) {
        this.storeCategory = storeCategory;
    }

    /**
     * get the logo of the store
     * @return the logo of the store
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Set the logo of the store
     * @param logo the new logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * get the mark of the store
     * @return the mark of the store
     */
    public int getMark() {
        return mark;
    }

    /**
     * Set the mark of the store
     * @param mark the new mark
     */
    public void setMark(int mark) {
        this.mark = mark;
    }
}
