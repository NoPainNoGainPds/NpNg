package utils.daoUtils;

import utils.Constants;
import utils.DAO;

public class BoutiqueDAO extends DAO {
    public BoutiqueDAO()
    {
        super(Constants.DB.getConnection());
    }
    @Override
    public boolean create(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public Object find(int id) {
        return null;
    }
}
