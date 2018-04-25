package utils.daoUtils;

import model.Client;
import utils.ConnectionServer;
import utils.DAO;

import java.util.ArrayList;

public class ClientDAO extends DAO<Client> {
    public ClientDAO(ConnectionServer conserv)
    {
        super(conserv);
    }
    @Override
    public boolean create(Client obj) {
        return false;
    }

    @Override
    public boolean delete(Client obj) {
        return false;
    }

    @Override
    public boolean update(Client obj) {
        return false;
    }

    @Override
    public Client find(int id) {
        return null;
    }

    @Override
    public ArrayList<Client> findFromReference(int id) {
        return null;
    }

    @Override
    public ArrayList<Client> findFromReference() {
        try {
            String str = "{\"name\":\"Client\",\"id\":-1}";
            this.connection.send(str);
            ArrayList<Client> liste = new ArrayList<>();
            boolean recieved = false;
            while(!recieved)
            {
                Object b = (Object)this.connection.recieve(Client.class);
                if (b != null) {
                    Client b2 = (Client) b;
                    liste.add(b2);
                } else {
                    recieved = true;
                }
            }
            return liste;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
