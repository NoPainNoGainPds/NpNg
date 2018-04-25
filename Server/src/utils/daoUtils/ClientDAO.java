package utils.daoUtils;

import model.ClientModel;
import utils.DAO;

import java.util.ArrayList;

public class ClientDAO extends DAO<ClientModel> {
    public ClientDAO( )
    {
        super();
    }
    @Override
    public boolean create(ClientModel obj) {
        return false;
    }

    @Override
    public boolean delete(ClientModel obj) {
        return false;
    }

    @Override
    public boolean update(ClientModel obj) {
        return false;
    }

    @Override
    public ClientModel find(int id) {
        return null;
    }

    @Override
    public ArrayList<ClientModel> findFromReference(int id) {
        return null;
    }

    @Override
    public ArrayList<ClientModel> findFromReference() {
    }
}
