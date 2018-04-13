package R3;

import controller.Client;
import model.Boutique;
import model.Emplacement;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.EmplacementDAO;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class which represents the algorithm to use to assign a location to a store
 */
public class Algorithm {
    /**
     * the list of all the locations in the database
     */
    private ArrayList<Emplacement> locationList;
    /**
     * the list of all the locations in the database, stored by mark
     */
    private ArrayList<MarkedLocation> markedLocationList;
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
        markedLocationList = new ArrayList<MarkedLocation>();
        this.bDAO = bDAO;
        storeList = bDAO.findFromReference();
        markedStoreList = new ArrayList<MarkedStore>();
    }

    /**
     * Method to sort the store and the location lists thanks to the criteria
     */
    public void sortLists() {
        for(int i = 0 ; i < storeList.size() ; i++) {
            /*
            Mark starts to 0 and is increased thanks to criteria
             */
            int mark = 0;
            /*
            Begin the algorithm by modifying the mark
             */
            /*
            Then create a MarkedStore with the mark and add it to the list to sort
             */
            MarkedStore ms = new MarkedStore(storeList.get(i), mark);
            markedStoreList.add(ms);
        }

        for(int i = 0 ; i < locationList.size() ; i++) {
            /*
            Mark starts to 0 and is increased thanks to criteria
             */
            int mark = 0;
             /*
            Begin the algorithm by modifying the mark
             */
            /*
            Then create a MarkedLocation with the mark and add it to the list to sort
             */
            MarkedLocation ml = new MarkedLocation(mark, locationList.get(i));
            markedLocationList.add(ml);
        }
        /*
        Sort the lists by mark thanks to Collections methods
         */
        Collections.sort(markedStoreList, Collections.reverseOrder());
        Collections.sort(markedLocationList, Collections.reverseOrder());
    }

    /**
     * Method to assign a location to a store after having sorted the lists
     */
    public void assignLocationsToStores() {
        /*
        Sort the lists
         */
        sortLists();
        /*
        If there are less locations, take the number of locations for size, else the number of stores
         */
        int max;
        if(markedLocationList.size() <= markedStoreList.size()) {
            max = markedLocationList.size();
        }
        else {
            max = markedStoreList.size();
        }
        /*
        The better location is assigned to the better store
         */
        for(int i = 0 ; i < max ; i++) {
            markedStoreList.get(i).getStore().setEmplacement(markedLocationList.get(i).getLocation());
        }
    }
}
