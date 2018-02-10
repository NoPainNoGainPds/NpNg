package utils.daoUtils;

import model.Emplacement;
import utils.Constants;
import utils.DAO;

import java.util.ArrayList;

public class EmplacementDAO extends DAO<Emplacement> {
    public EmplacementDAO()
    {
        super(Constants.DB.getConnection());
    }
    @Override
    public boolean create(Emplacement obj) {
        return false;
    }

    @Override
    public boolean delete(Emplacement obj) {
        return false;
    }

    @Override
    public boolean update(Emplacement obj) {
        return false;
    }

    @Override
    public Emplacement find(int id) {
        return null;
    }

    @Override
    public ArrayList<Emplacement> findFromReference(int id) {
        return null;
    }
}
