package utils.daoUtils;

import model.Produit;
import org.junit.Test;
import utils.Constants;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProduitDAOTest {
    private ProduitDAO produitDAO = new ProduitDAO(Constants.conServ);

    @Test
    public void find() {
        Produit produit = new Produit("Iphone X", 1);
        Produit produit2 = produitDAO.find(1);
        assertEquals(produit.getNom(), produit2.getNom());
    }
}