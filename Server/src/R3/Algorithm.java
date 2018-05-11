package R3;

import model.Boutique;
import model.Emplacement;
import model.MarkedLocation;
import model.MarkedStore;
import org.apache.log4j.Logger;
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
     * A logger. Use to have a trace of what happen during the execution.
     */
    Logger logger;

    /**
     * Constructor
     *
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
        logger = Logger.getLogger(Algorithm.class);
    }

    /**
     * Method to sort the store and the location lists thanks to the criteria
     */
    public void sortLists() {
        /*
        Get the array with the criteria's values
         */
        int[] storeCriteria = bDAO.getStoreCriteria();
        int[] locationCriteria =eDAO.getLocationCriteria();

        for (int i = 0; i < storeList.size(); i++) {
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

        for (int i = 0; i < locationList.size(); i++) {
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
        If there are less locations, take the number of locations for size, else, the number of stores
         */
        int max;
        if (markedLocationList.size() <= markedStoreList.size()) {
            max = markedLocationList.size();
        } else {
            max = markedStoreList.size();
        }
        /*
        The better location is assigned to the better store
         */
        for (int i = 0; i < max; i++) {
            markedStoreList.get(i).getStore().setEmplacement(markedLocationList.get(i).getLocation());
            markedStoreList.get(i).getStore().setLocated(true);
            markedLocationList.get(i).getLocation().setAssigned(true);
        }
    }

    /**
     * Method to create max locations in the database
     *
     * @param max the number of locations to create
     */
    public void createLocations(int max) {
        for (int i = 0; i < max; i++) {
            Emplacement location = new Emplacement();
            eDAO.create(location);
        }
    }

    /**
     * Method to create max stores in the database
     *
     * @param max the number of stores to create
     */
    public void createStores(int max) {
        for (int i = 0; i < max; i++) {
            Boutique store = new Boutique();
            bDAO.create(store);
        }
    }

    /**
     * Method to unassign all the locations in the database
     */
    public void unassignAllLocations() {
        for (Emplacement location : locationList) {
            eDAO.unassign(location);
        }
    }

    /**
     * Method to unlocate all the stores in the database
     */
    public void unlocateAllStores() {
        for (Boutique store : storeList) {
            bDAO.unlocate(store);
        }
    }

}

