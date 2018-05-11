package controller;

import R3.Algorithm;
import R3.AlgorithmFee;
import R3.Algorithm;
import R3.Facture;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import utils.daoUtils.*;

import java.io.BufferedOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the sender
 */
public class Sender {
    /**
     * The output stream
     */
    private BufferedOutputStream writer;
    /**
     * The object mapper
     */
    private ObjectMapper mapper;
    /**
     * The store
     */
    private BoutiqueDAO bDAO;
    /**
     * The store category
     */
    private CategorieBoutiqueDAO cbDAO;
    /**
     * The product category
     */
    private CategorieProduitDAO cpDAO;
    /**
     * The location
     */
    private EmplacementDAO eDAO;
    /**
     * The provider
     */
    private FournisseurDAO fDAO;
    /**
     * The product
     */
    private ProduitDAO pDAO;
    /**
     * The output storage
     */
    private StockSortieDAO ssDAO;
    /**
     * The input storage
     */
    private StockEntreeDAO esDAO;
    /**
     * The Client
     */
    private ClientDAO cDAO;
    /**
     * The fee
     */
    private RedevanceDAO rDAO;
    /**
     *
     */
    private ProfilDAO profDAO;

    /**
     *
     */
    private ParcoursDAO parcoursDAO;

    private BonLivraisonDAO blDAO;

    private CauseSortieStockDAO cssDAO;

    private PurchaseDAO purchaseDAO;

    /**
     * Constructor
     * @param database the connection to the database
     * @param mapper the object mapper
     * @param writer the output stream
     * @param client the client
     */
    public Sender(Connection database, ObjectMapper mapper,BufferedOutputStream writer,Client client)
    {
        this.bDAO = new BoutiqueDAO(database,client);
        this.cbDAO = new CategorieBoutiqueDAO(database);
        this.cpDAO = new CategorieProduitDAO(database);
        this.eDAO = new EmplacementDAO(database);
        this.fDAO = new FournisseurDAO(database);
        this.pDAO = new ProduitDAO(database);
        this.ssDAO = new StockSortieDAO(database);
        this.esDAO = new StockEntreeDAO(database);
        this.rDAO = new RedevanceDAO(database);
        this.blDAO = new BonLivraisonDAO(database);
        this.cssDAO = new CauseSortieStockDAO(database);
        this.cDAO = client.getcDAO();
        this.profDAO = new ProfilDAO(database);
        this.purchaseDAO = new PurchaseDAO(database);
        this.parcoursDAO = new ParcoursDAO(database);
        this.mapper = mapper;
        this.mapper.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        this.writer = writer;
    }

    /**
     * Method to send all the store which are in the database
     */
    public void sendAllStore()
    {
        try {
            ArrayList<Boutique> objReturn = this.bDAO.findFromReference();
            System.out.println(objReturn.size());
            for(Boutique b : objReturn)
            {
                this.mapper.writeValue(this.writer,b);
                this.writer.write("\n".getBytes());
                this.writer.flush();
            }
            this.writer.write("null\n".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to send the products if id is not equal to -1
     * @param id the id
     */
    public void sendProducts(int id)
    {
        if(id!=-1)
        {
            try {
                ArrayList<Produit> liste = this.pDAO.findFromReference(id);
                for (Produit p : liste) {
                    this.mapper.writeValue(this.writer,p);
                    this.writer.write("\n".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }catch (JsonMappingException e) {
                e.printStackTrace();
            } catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void sendClient(int id)
    {
        if(id==-1)
        {
            try {
                ArrayList<ClientModel> liste = this.cDAO.findFromReference();
                for (ClientModel p : liste) {
                    this.mapper.writeValue(this.writer,p);
                    this.writer.write("\n".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }catch (JsonMappingException e) {
                e.printStackTrace();
            } catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to send the stores which sell the product productName
     * @param productName name of the product
     */
    public void sendStoreWhoSale(String productName)
    {
        try {
            if (!productName.equals("")) {
                Integer[] liste = this.bDAO.findWhoSale(productName);
                for(int i = 0;i<liste.length;i++) {
                    this.mapper.writeValue(this.writer,liste[i]);
                    this.writer.write("\n".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
            else
            {
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
        }catch(JsonMappingException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to send what is getting out from storages
     * @param inputFromClient what the client wants
     */
    public void sendSortieStock(InputFromClient inputFromClient)
    {
        try {
            if (!inputFromClient.getRef().equals("")) {
                ArrayList<StockSortie> liste = this.ssDAO.findFromReference(inputFromClient.getId(),Integer.parseInt(inputFromClient.getRef()));
                for(int i = 0;i<liste.size();i++) {
                    this.mapper.writeValue(this.writer,liste.get(i));
                    this.writer.write("\n".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
            else
            {
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
        }catch(JsonMappingException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to send what is entering storage
     * @param inputFromClient the input from the client
     */
    public void sendEntreeStock(InputFromClient inputFromClient)
    {
        try {
            if (!inputFromClient.getRef().equals("")) {
                ArrayList<StockEntree> liste = this.esDAO.findFromReference(inputFromClient.getId(),Integer.parseInt(inputFromClient.getRef()));
                for(int i = 0;i<liste.size();i++) {
                    this.mapper.writeValue(this.writer,liste.get(i));
                    this.writer.write("\n".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
            else
            {
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
        }catch(JsonMappingException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Methode to insert product from client
     * @param product the product to insert
     */
    public void insertProduct(Produit product)
    {
        try {
            Boolean b = this.pDAO.create(product);
            this.mapper.writeValue(this.writer, b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
            System.out.println("creation produit");
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void updateProfil(int id)
    {
        try{
            Boolean b = false;
            if(id==-1)
            {
                b = this.cDAO.delAllProfil();
            }else if (id==1)
            {
                b = this.cDAO.attrProf();
            }
            this.mapper.writeValue(this.writer,b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void sendProfilClient(int id)
    {
        try
        {
            ArrayList<String> liste = this.profDAO.getProfilFromClient(id);
            for(String r : liste)
            {
                this.mapper.writeValue(this.writer,r);
                this.writer.write("\n".getBytes());
                this.writer.flush();
            }
            this.writer.write("null\n".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void sendCauseSortieStock()
    {
        try
        {
            ArrayList<String> liste = this.cssDAO.getCauseSortieStock();
            for(String r : liste)
            {
                this.mapper.writeValue(this.writer,r);
                this.writer.write("\n".getBytes());
                this.writer.flush();
            }
            this.writer.write("null\n".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Send all fees
     */
    public void sendAllfees()
    {
        try {
            ArrayList<Redevance> objReturn = this.rDAO.findFromReference();
            System.out.println(objReturn.size());
            for(Redevance r : objReturn)
            {
                this.mapper.writeValue(this.writer,r);
                this.writer.write("\n".getBytes());
                this.writer.flush();
            }
            this.writer.write("null\n".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * find fee of a month and a year we choose
     * @param inputFromClient contain the month and the year.
     */
    public void sendFee(InputFromClient inputFromClient)
    {
        try {
            if (!inputFromClient.getRef().equals("")) {
                ArrayList<Redevance> liste = this.rDAO.findFromReference(inputFromClient.getId(),Integer.parseInt(inputFromClient.getRef()));
                for(int i = 0;i<liste.size();i++) {
                    this.mapper.writeValue(this.writer,liste.get(i));
                    this.writer.write("\n".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
            else
            {
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
        }catch(JsonMappingException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to find the stores which have to pay
     * @return A list of the stores.
     */
    public void sendStoreWhoPay()
    {
        try {
            ArrayList<Boutique> objReturn = this.bDAO.findWhoPay();
            System.out.println(objReturn.size());
            for(Boutique b : objReturn)
            {
                this.mapper.writeValue(this.writer,b);
                this.writer.write("\n".getBytes());
                this.writer.flush();
            }
            this.writer.write("null\n".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Add a new fee in the database (not used).
     * @param inputFromClient .
     * @return true if it works, false else.
     */
    public void insertFee(InputFromClient inputFromClient)
    {
        Redevance fee = null;
        try {
            Boolean b = this.rDAO.create(fee);
            this.mapper.writeValue(this.writer, b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
            System.out.println("creation redevance");
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Delete a fee from the database.
     * @param red The fee to delete.
     * @return true if it works, false else.
     */
    public boolean deleteFee(Redevance red)
    {
        try {
            Boolean b = this.rDAO.delete(red);
            this.mapper.writeValue(this.writer, b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
            System.out.println("suppression redevance");
            return true;

        }catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void dellAllProfil(){
        this.cDAO.delAllProfil();
    }
    public void attPurchase() {
        try {
            Boolean b =this.purchaseDAO.generatePurchase();
            this.mapper.writeValue(this.writer,b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Calculate and assign a fee to all stores
     */
    public void calculFee() {
        AlgorithmFee alg = new AlgorithmFee(this.bDAO,this.rDAO);
        alg.assignFeeToStore();
    }

    /**
     * send a bill in PDF.
     * @param dest name of PDF
     * @param id_redevance id of the fee
     * @return true if it works, false else..
     */
    public boolean sendFacture(String dest, int id_redevance){
        try {
            Facture f = new Facture(this.bDAO,this.rDAO);
            String b = f.createPdf(dest, id_redevance);
            this.mapper.writeValue(this.writer, b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
            System.out.println("creation redevance");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendAllProfilsWithoutParcours()
    {
        try {
            ArrayList<Profil> listProfil = this.profDAO.getProfilsWithoutParcours();
            System.out.println(listProfil.size());
            for(Profil p : listProfil)
            {
                this.mapper.writeValue(this.writer,p);
                this.writer.write("\n".getBytes());
                this.writer.flush();
            }
            this.writer.write("null\n".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to send all stores with category
     */
    public void sendAllStoreWithCategory()
    {
        try {
            ArrayList<Boutique> listStore = this.bDAO.getStoreWithCategory();
            System.out.println(listStore.size());
            for(Boutique b : listStore)
            {
                this.mapper.writeValue(this.writer,b);
                this.writer.write("\n".getBytes());
                this.writer.flush();
            }
            this.writer.write("null\n".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void createStockEntree(Client cl)
    {
        try
        {
            Boolean b = true;
            //demande de l'objet
            this.mapper.writeValue(this.writer,b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
            //reception de l'objet
            String str = cl.read();
            StockEntree seFromClient = this.mapper.readValue(str, StockEntree.class);
            b = this.esDAO.create(seFromClient);
            //envoie de la reponse
            this.mapper.writeValue(this.writer,b);
            this.writer.write("\n".getBytes());
            this.writer.flush();

        }catch(JsonMappingException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createStockSortie (Client cl){
        try
        {
            Boolean b = true;
            //demande de l'objet
            this.mapper.writeValue(this.writer,b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
            //reception de l'objet
            String str = cl.read();
            StockSortie ssFromClient = this.mapper.readValue(str, StockSortie.class);
            b = this.ssDAO.create(ssFromClient);
            //envoie de la reponse
            this.mapper.writeValue(this.writer,b);
            this.writer.write("\n".getBytes());
            this.writer.flush();

        }catch(JsonMappingException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void createBonLivraison(Client cl)
    {
        try
        {
            Boolean b = true;
            //demande de l'objet
            this.mapper.writeValue(this.writer,b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
            //reception de l'objet
            String str = cl.read();
            BonLivraison blFromClient = this.mapper.readValue(str, BonLivraison.class);
            b = this.blDAO.create(blFromClient);
            //envoie de la reponse
            this.mapper.writeValue(this.writer,b);
            this.writer.write("\n".getBytes());
            this.writer.flush();

        }catch(JsonMappingException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void assignLocationsToStores() {
        Algorithm algo = new Algorithm(this.eDAO, this.bDAO);
        algo.assignLocationsToStores();
    }

    public void unlocateAllStores() {
        Algorithm algo = new Algorithm(this.eDAO, this.bDAO);
        algo.unlocateAllStores();
    }

    public void unassignAllLocations() {
        Algorithm algo = new Algorithm(this.eDAO, this.bDAO);
        algo.unassignAllLocations();
    }

    public void assignAllPaths(){
        try {
            // Get all profiles without Parcours
            ArrayList<Profil> listProfil = this.profDAO.getProfilsWithoutParcours();
            for(Profil p : listProfil) {
                int idProfil = p.getValue();
                String nomCompletProfil = p.getNom();
                String nomProfil;
                List<Boutique> listBoutiques;
                if (p.getNom().length() > 2) {
                    String plus = nomCompletProfil.substring(nomCompletProfil.length() - 2);
                    if (plus.equals("++")) {
                        nomProfil = nomCompletProfil.substring(0,nomCompletProfil.length()-2);
                        // renvoie les boutiques par ordre de frequentation selon nom profil et nb boutiques
                        listBoutiques = bDAO.getStoresForCategory(nomProfil,8);
                    } else if ((plus.charAt(1) + "").equals("+")) {
                        nomProfil = nomCompletProfil.substring(0,nomCompletProfil.length()-1);
                        listBoutiques = bDAO.getStoresForCategory(nomProfil,6);
                        // ajouter deux autres boutiques parmi les plus fréquentées
                        listBoutiques.addAll(bDAO.getPopularStoresExcludingCategory(nomProfil,2));
                    } else {
                        nomProfil = nomCompletProfil;
                        listBoutiques = bDAO.getStoresForCategory(nomProfil,4);
                        listBoutiques.addAll(bDAO.getPopularStoresExcludingCategory(nomProfil,4));

                    }
                    System.out.println("Nom profil : " + nomProfil + ", plus : " + plus);
                    // assigne la liste de boutiques créée à un nouveu parcours pour le profil en question
                    Parcours path = new Parcours();
                    path.setStoreList(listBoutiques);
                    path.setName("parcours "+nomCompletProfil);
                    parcoursDAO.create(path);
                }
            }


            for(Profil p : listProfil)
            {
                this.mapper.writeValue(this.writer,p);
                this.writer.write("\n".getBytes());
                this.writer.flush();
            }
            this.writer.write("null\n".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void sendParcours(InputFromClient input)
    {
        int profil = this.cDAO.getFirstProfil(input.getId());

        try {
            ArrayList<Integer> listeInt = this.parcoursDAO.emplacementParcours(profil);
            System.out.println(listeInt.size());
            for(Integer b : listeInt)
            {
                this.mapper.writeValue(this.writer,b);
                this.writer.write("\n".getBytes());
                this.writer.flush();
            }
            this.writer.write("null\n".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
