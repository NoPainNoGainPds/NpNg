package utils.daoUtils;


import model.CategorieBoutique;
import utils.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategorieBoutiqueDAO extends DAO<CategorieBoutique> {
    public CategorieBoutiqueDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(CategorieBoutique obj) {
        return false;
    }

    @Override
    public boolean delete(CategorieBoutique obj) {
        return false;
    }

    @Override
    public boolean update(CategorieBoutique obj) {
        return false;
    }

    @Override
    public CategorieBoutique find(int id) {
        return null;
    }

    @Override
    public ArrayList<CategorieBoutique> findFromReference(int id) {
        return null;
    }

    @Override
    public ArrayList<CategorieBoutique> findFromReference() {
        try
        {
            Statement stmt =  this.connection.createStatement();
            String requete = "SELECT idCategorieBoutique,nomCategorie FROM categorieBoutique";
            ResultSet res = stmt.executeQuery(requete);
            ArrayList<CategorieBoutique> listBoutique = new ArrayList<>();

            while(res.next())
            {
                listBoutique.add(new CategorieBoutique(res.getString(2),res.getInt(1)));
            }
            return listBoutique;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
