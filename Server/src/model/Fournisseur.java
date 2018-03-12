package model;

import utils.Constants;
import utils.DAO;
import utils.ModelObject;
import utils.daoUtils.FournisseurDAO;

public class Fournisseur implements ModelObject {
    @Override
    public DAO getDaoClass() {
        return new FournisseurDAO(null);
    }
}
