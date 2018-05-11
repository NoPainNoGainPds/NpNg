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
        private TextLabel <JTextField> montant;
        private JTextField montantTextField;


        public EntryIntoStorageForm(){
            this.view();
        }

        public void view (){
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

            montantTextField = new JTextField(20);
            montantTextField.setPreferredSize(new Dimension(250,50));
            montant = new TextLabel(montantTextField, new JLabel("Montant"));
            this.add(montant);
        }

        public JTextField getMontantTextField() {
            return montantTextField;
        }

        public JTextField getIdProduitTextField() {
            return idProduitTextField;
        }

        public JTextField getNomProduitTextField() {
            return nomProduitTextField;
        }

        public JTextField getQuantiteTextField() {
            return quantiteTextField;
        }
    }
