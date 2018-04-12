package R3;

import model.Boutique;
import model.Emplacement;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.EmplacementDAO;

import java.util.ArrayList;

/**
 * Class which represents the algorithm to use to assign a location to a store
 */
public class Algorithm {
    /**
     * the list of all the locations in the database
     */
    private ArrayList<Emplacement> locationList;
    /**
     * the list of all the stores in the database
     */
    private ArrayList<Boutique> storeList;
    /**
     * the list of all the stores in the database, sorted by mark
     */
    private ArrayList<MarkedStore> markedStoreList;
    /**
     * used to get all the locations
     */
    private EmplacementDAO eDAO;
    /**
     * used to get all the stores
     */
    private BoutiqueDAO bDAO;

    /**
     * Constructor
     * @param eDAO the location DAO
     * @param bDAO the store DAO
     */
    public Algorithm(EmplacementDAO eDAO, BoutiqueDAO bDAO) {
        this.eDAO = eDAO;
        locationList = eDAO.findFromReference();
        this.bDAO = bDAO;
        storeList = bDAO.findFromReference();
    }

    /**
     * Method to sort the store and the location lists thanks to the criteria
     */
    public void sortLists() {
        for(int i = 0 ; i < storeList.size() ; i++) {
            int mark = 0;
        }

        for(int i = 0 ; i < locationList.size() ; i++) {
            int mark = 0;
        }
    }

    /**
     * Method to assign a location to a store after having sorted the lists
     */
    public void assignLocationsToStores() {
        sortLists();
        int max;
        if(locationList.size() <= storeList.size()) {
            max = locationList.size();
        }
        else {
            max = storeList.size();
        }

        for(int i = 0 ; i < max ; i++) {
            storeList.get(i).setEmplacement(locationList.get(i));
        }
    }
}
