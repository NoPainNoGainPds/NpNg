package vue;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class OutputStorageForm extends JPanel {

    /**
     * The Quantity
     */
    private TextLabel <JTextField> quantite;
    private JTextField quantiteTextField;

    private TextLabel<JTextField> nomProduit;
    private JTextField nomProduitTextField;

    private TextLabel<JTextField> idProduit;
    private JTextField idProduitTextField;


    public OutputStorageForm() {
        this.setLayout(new MigLayout("inset 0 20 20 20 ", "[fill, grow]", "[fill, grow]"));

        idProduitTextField = new JTextField(20);
        idProduitTextField.setPreferredSize(new Dimension(250,50));
        idProduit = new TextLabel(idProduitTextField, new JLabel("Id du produit"));
        this.add(idProduit);

        nomProduitTextField = new JTextField(20);
        nomProduitTextField.setPreferredSize(new Dimension(250,50));
        nomProduit = new TextLabel(nomProduitTextField, new JLabel("Nom du produit"));
        this.add(nomProduit);

        quantiteTextField = new JTextField(20);
        quantiteTextField.setPreferredSize(new Dimension(250, 50));
        quantite = new TextLabel(quantiteTextField, new JLabel("Quantite"));
        this.add(quantite);
    }

    public JTextField getQuantiteTextField() {
        return quantiteTextField;
    }

    public JTextField getNomProduitTextField() {
        return nomProduitTextField;
    }

    public JTextField getIdProduitTextField() {
        return idProduitTextField;
    }
}
