package utils.daoUtils;

import model.Boutique;
import utils.Constants;
import utils.DAO;

public class BoutiqueDAO extends DAO<Boutique> {
    public BoutiqueDAO()
    {
        super(Constants.DB.getConnection());
    }
    @Override
    public boolean create(Boutique obj) {
        return false;
    }

    @Override
    public boolean delete(Boutique obj) {
        return false;
    }

    @Override
    public boolean update(Boutique obj) {
        return false;
    }

    @Override
    public Boutique find(int id) {
        return null;
    }
}
