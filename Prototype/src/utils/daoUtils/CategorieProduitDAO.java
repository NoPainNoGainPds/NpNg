package utils.daoUtils;

import model.CategorieProduit;
import utils.DAO;

import java.sql.Connection;
import java.util.ArrayList;

public class CategorieProduitDAO extends DAO<CategorieProduit>{
    public CategorieProduitDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(CategorieProduit obj) {
        return false;
    }

    @Override
    public boolean delete(CategorieProduit obj) {
        return false;
    }

    @Override
    public boolean update(CategorieProduit obj) {
        return false;
    }

    @Override
    public CategorieProduit find(int id) {
        return null;
    }

    @Override
    public ArrayList<CategorieProduit> findFromReference(int id) {
        return null;
    }

    @Override
    public ArrayList<CategorieProduit> findFromReference() {
        return null;
    }
}
