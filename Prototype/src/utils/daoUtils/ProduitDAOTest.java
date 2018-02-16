package utils.daoUtils;

import model.Produit;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProduitDAOTest {
    private ProduitDAO produitDAO = new ProduitDAO();

    @Test
    public void find() {
        Produit produit = new Produit("Iphone X", 1);
        Produit produit2 = produitDAO.find(1);
        assertEquals(produit.getNom(), produit2.getNom());
    }
}