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
        System.out.println("storelist size :" +storeList.size());
        System.out.println("location list size : "+locationList.size());
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
            mark += storeCriteria[0];
            switch (storeList.get(i).getCategorieBoutique().getNom()) {
                case "sport" :
                    mark += 2;
                    break;
                case "mobilier" :
                    mark += 3;
                    break;
                case "tech" :
                    mark += 4;
                    break;
                case "alimentaire" :
                    mark += 5;
                    break;
                case "education" :
                    mark += 1;
                    break;
                case "mode" :
                    mark += 6;
                    break;
                case "grande surface" :
                    mark += 10;
                    break;
                default :
                    System.out.println("Category not known");
                    break;
            }
            mark += storeCriteria[1];
            switch(bDAO.getRenommee(storeList.get(i))) {
                case "*" :
                    mark += 5;
                    break;
                case "A" :
                    mark += 4;
                    break;
                case "B" :
                    mark += 3;
                    break;
                case "C":
                    mark += 2;
                    break;
                case "D" :
                    mark += 1;
                    break;
                default :
                    System.out.println("Renommee not known");
                    break;
            }

            mark += storeCriteria[2];
            switch (bDAO.getGamme(storeList.get(i))) {
                case "A" :
                    mark += 3;
                    break;
                case "B" :
                    mark += 2;
                    break;
                case "C" :
                    mark += 1;
                    break;
                default :
                    System.out.println("Gamme not known");
                    break;
            }
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
             mark += locationCriteria[0];
             int surface = locationList.get(i).getSuperficie();
             if(surface <= 250)
                 mark += 1;
             if(surface > 250 && surface <= 850)
                 mark += 2;
             if(surface > 850 && surface <= 1200)
                 mark += 3;
             if(surface > 1200 && surface < 1500)
                 mark += 4;
             if(surface > 1500 && surface <= 2000)
                 mark += 5;
             if(surface > 2000 && surface <= 5000)
                 mark += 6;
             if(surface > 5000 && surface <= 6000)
                 mark += 7;
             if (surface > 6000)
                 mark += 8;

             mark += locationCriteria[1];
             switch(locationList.get(i).getCat()) {
                 case "*" :
                     mark += 5;
                     break;
                 case "A" :
                     mark += 4;
                     break;
                 case "B" :
                     mark += 3;
                     break;
                 case "C" :
                     mark += 2;
                     break;
                 case "D" :
                     mark += 1;
                     break;
                 default :
                     System.out.println("Category not known");
                     break;
             }
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
        unassignAllLocations();
        unlocateAllStores();
        for (int i = 0; i < max; i++) {
            markedStoreList.get(i).getStore().setEmplacement(markedLocationList.get(i).getLocation());
            bDAO.setLocation(markedStoreList.get(i).getStore(),markedLocationList.get(i).getLocation());
            bDAO.setLocated(markedStoreList.get(i).getStore());
            eDAO.setAssigned(markedLocationList.get(i).getLocation());
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

    public ArrayList<Emplacement> getLocationList() {
        return locationList;
    }

}

