package model;

import utils.Constants;
import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.FournisseurDAO;

/**
 * Represents a provider
 */
public class Fournisseur implements ModelObject {
    /**
     * get the FournisseurDAO object
     * @return the DAO
     */
    @Override
    public DAO getDaoClass() {
        return new FournisseurDAO(null);
    }
}
