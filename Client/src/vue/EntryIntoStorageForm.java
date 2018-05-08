    package vue;

    import model.Boutique;
    import net.miginfocom.swing.MigLayout;

    import javax.swing.*;
    import java.awt.*;

    public class EntryIntoStorageForm extends JPanel{

        private TextLabel<JTextField> nomProduit;
        private JTextField nomProduitTextField;

        private TextLabel<JTextField> idProduit;
        private JTextField idProduitTextField;
        /**
         * Textfield to enter the quantity
         */
        private TextLabel <JTextField> quantite;
        private JTextField quantiteTextField;
        /**
         * Textfield to enter unitary cost
         */
        private TextLabel <JTextField> coutUnitaire;
        private JTextField coutUnitaireTextField;


        public EntryIntoStorageForm(){
            this.setLayout(new MigLayout("inset 0 20 20 20 ", "[fill, grow]", "[fill, grow]"));

            idProduitTextField = new JTextField(20);
            idProduitTextField.setPreferredSize(new Dimension(250, 50));
            idProduit = new TextLabel(idProduitTextField, new JLabel("id du produit"));
            this.add(idProduit);

            nomProduitTextField = new JTextField(20);
            nomProduitTextField.setPreferredSize(new Dimension(250, 50));
            nomProduit = new TextLabel(nomProduitTextField, new JLabel("nom du produit"));
            this.add(nomProduit);

            quantiteTextField = new JTextField(20);
            quantiteTextField.setPreferredSize(new Dimension(250,50));
            quantite = new TextLabel(quantiteTextField, new JLabel("Quantite"));
            this.add(quantite);

            coutUnitaireTextField = new JTextField(20);
            coutUnitaireTextField.setPreferredSize(new Dimension(250,50));
            coutUnitaire = new TextLabel(coutUnitaireTextField, new JLabel("Cout Unitaire"));
            this.add(coutUnitaire);
        }

    }
