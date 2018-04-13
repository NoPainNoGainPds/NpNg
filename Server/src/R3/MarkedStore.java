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
public class MarkedStore implements Comparable{
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
     * @param mark mark of the store
     * @param store the store
     */
    public MarkedStore(Boutique store, int mark) {
        this.store = store;
        this.mark = mark;
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

    /**
     * Get the store linked to the marked store
     * @return the store
     */
    public Boutique getStore() {
        return store;
    }

    /**
     * Set the store linked to the marked store
     * @param store the new store
     */
    public void setStore(Boutique store) {
        this.store = store;
    }

    /**
     * Method to compare two stores by their mark
     * @param o the store to compare to this
     * @return
     */
    @Override
    public int compareTo(Object o) {
        MarkedStore ms = (MarkedStore) o;
        return ((Integer) this.mark).compareTo((Integer) ms.getMark());
    }
}
