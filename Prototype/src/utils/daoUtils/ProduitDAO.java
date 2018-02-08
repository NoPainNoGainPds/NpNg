package utils.daoUtils;

import model.Produit;
import utils.Constants;
import utils.DAO;


public class ProduitDAO extends DAO<Produit> {
    public ProduitDAO()
    {
        super(Constants.DB.getConnection());
    }
    @Override
    public boolean create(Produit obj) {
        return false;
    }

    @Override
    public boolean delete(Produit obj) {
        return false;
    }

    @Override
    public boolean update(Produit obj) {
        return false;
    }

    @Override
    public Produit find(int id) {
        return null;
    }
}
