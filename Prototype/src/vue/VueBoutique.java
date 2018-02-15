package vue;

import model.Boutique;
import model.Produit;
import utils.Constants;
import utils.MyListModel;
import utils.daoUtils.ProduitDAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueBoutique extends JFrame {
    private Boutique boutique;
    private JList<Produit> jlistProduit;
    public VueBoutique(Boutique boutique)
    {
        super("Boutique");
        this.boutique = boutique;
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Recover the products list.
        ProduitDAO pDao = new ProduitDAO();
        ArrayList<Produit> listProduit = pDao.findFromReference(boutique.getId());
        MyListModel<Produit> listModel = new MyListModel<>(listProduit);
        //Data in a jlist for displaying
        this.jlistProduit = new JList<Produit>(listModel);
        //Display and Layout
        JScrollPane jscrollPane = new JScrollPane(jlistProduit);
        JPanel panel = new JPanel();
        panel.add(jscrollPane);
        this.add(panel, BorderLayout.EAST);
        panelDroit();
        this.setVisible(true);
    }

    /**
     * This Method add the right panel
     */

    private void panelDroit()
    {
        JPanel panel = new JPanel();
        // Button to Add a product
        JButton ajout = new JButton("Ajouter");
        //Button to delete a product
        JButton suppr = new JButton("Supprimer");
        //Changes of products Button
        JButton modif = new JButton("Modifier");
        panel.add(ajout);
        panel.add(suppr);
        panel.add(modif);
        //Additional Controllers
        ajout.addActionListener(event ->
        {
            //action ici :)
        });
        suppr.addActionListener(event ->
        {
            //et la aussi pour suppr
        });
        modif.addActionListener(event ->
        {
            new UpdateWindow<>(jlistProduit.getSelectedValue());
        });
        this.add(panel,BorderLayout.WEST);

    }
}
