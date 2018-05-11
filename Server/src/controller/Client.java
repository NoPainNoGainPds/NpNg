package controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.Database;
import model.Boutique;
import model.InputFromClient;
import model.Produit;
import model.Redevance;
import utils.daoUtils.*;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Represents a client
 */
public class Client extends Thread {
    /**
     * The socket
     */
    private Socket skt;
    /**
     * the connection
     */
    private Connection database;
    /**
     * the server
     */
    private Server server;
    /**
     * If the server is running
     */
    private boolean running = true;
    /**
     * The object mapper
     */
    private ObjectMapper mapper;
    //create DAO with Database connexion
    /**
     * To access the store with database
     */
    private BoutiqueDAO bDAO;
    /**
     * to access the store category with database
     */
    private CategorieBoutiqueDAO cbDAO;
    /**
     * to access the product category with database
     */
    private CategorieProduitDAO cpDAO;
    /**
     * to access the location with database
     */
    private EmplacementDAO eDAO;
    /**
     * to access the provider with database
     */
    private FournisseurDAO fDAO;
    /**
     * to access the product with database
     */
    private ProduitDAO pDAO;
    /**
     * to access the storage with database
     */
    private StockSortieDAO ssDAO;

    /**
     * to access the client with database
     */
    private ClientDAO cDAO;

    /**
     * to access the fee with database
     */
    private RedevanceDAO rDAO;

    private BonLivraisonDAO blDAO;

    private CauseSortieStockDAO cssDAO;

    /**
     * the sender
     */
    private Sender sender;

    /**
     * to read the input stream
     */
    private DataInputStream reader = null;
    /**
     * the output stream
     */
    private BufferedOutputStream writer = null;

    /**
     * Constructor
     * @param skt thge socket
     * @param sql the connection
     * @param server the server
     */
    public Client(Socket skt, Connection sql,Server server)
    {
        this.skt = skt;
        this.server = server;
        this.database = sql;
        this.bDAO = new BoutiqueDAO(this.database,this);
        this.cbDAO = new CategorieBoutiqueDAO(this.database);
        this.cpDAO = new CategorieProduitDAO(this.database);
        this.eDAO = new EmplacementDAO(this.database);
        this.fDAO = new FournisseurDAO(this.database);
        this.pDAO = new ProduitDAO(this.database);
        this.ssDAO = new StockSortieDAO(this.database);
        this.cDAO = new ClientDAO(this.database);
        this.rDAO = new RedevanceDAO(this.database);
        this.blDAO = new BonLivraisonDAO(this.database);
        this.cssDAO = new CauseSortieStockDAO(this.database);
    }

    /**
     * The run method
     */
    @Override
    public void run()
    {
        try {
            this.reader = new DataInputStream(this.skt.getInputStream());
            this.writer = new BufferedOutputStream(this.skt.getOutputStream());
            this.mapper = new ObjectMapper();
            this.sender = new Sender(this.database,this.mapper,this.writer,this);
            while (this.running) {
                //recuperation des info
                //System.out.println("running");
                if (!this.skt.isClosed()) {
                    String str = read();
                    InputFromClient inputFromClient = this.mapper.readValue(str, InputFromClient.class);
                    switch(inputFromClient.getName())
                    {
                        case "Store" ://exemple ici je sais que je vais utiliser le DAO Boutique
                        if(inputFromClient.getId()==-1)
                            sender.sendAllStore();//et la je renvoie le liste de toutes le boutiques
                        else if(inputFromClient.getId()==-2)
                            sender.sendStoreWhoSale(inputFromClient.getRef());
                        else if(inputFromClient.getId()==-3)
                            sender.sendStoreWhoPay();
                        break;
                        case "Product" :
                            sender.sendProducts(inputFromClient.getId());
                            break;
                        case "StockSortie" :
                            sender.sendSortieStock(inputFromClient);
                            break;
                        case "StockEntree" :
                            sender.sendEntreeStock(inputFromClient);
                            break;
                        case "UPDATE" :
                            sender.sendAllStore();
                            break;
                        case "newProduct" :
                            sender.insertProduct(this.mapper.readValue(read(),Produit.class));
                            break;
                        case "Client" :
                            sender.sendClient(inputFromClient.getId());
                            break;
                        case "UpdateProfil" :
                            sender.updateProfil(inputFromClient.getId());
                            break;
                        case "GetProfil" :
                            sender.sendProfilClient(inputFromClient.getId());
                            break;
                        case "DellProfil" :
                            sender.dellAllProfil();
                            break;
                        case "AttPurchase" :
                            sender.attPurchase();
                            break;
                        case "Redevance" :
                            if(inputFromClient.getId()==-1)
                            sender.sendAllfees();
                            else if(inputFromClient.getId()==-2)
                                sender.calculFee();
                            else sender.sendFee(inputFromClient);
                            break;
                        case "Facture" :
                            sender.sendFacture(inputFromClient.getRef() ,inputFromClient.getId());
                            break;
                        case "newFee" :
                            sender.insertFee(inputFromClient);
                            break;
                        case "deleteFee" :
                            sender.deleteFee(new Redevance(inputFromClient.getId()));
                            break;
                        case "CreateStockEntree" :
                            sender.createStockEntree(this);
                            break;
                        case "CreateStockSortie" :
                            sender.createStockSortie(this);
                            break;
                        case "CreateBonLivraison" :
                            sender.createBonLivraison(this);
                            break;
                        case "CauseSortieStock" :
                            sender.sendCauseSortieStock();
                        break;
                        case "SearchProfilsWithoutParcours" :
                            sender.sendAllProfilsWithoutParcours();
                            break;
                        case  "SearchStoreWithCategory":
                            sender.sendAllStoreWithCategory();
                            break;
                        case "AssignLocationsToStores" :
                            System.out.println("client serveur");
                            sender.assignLocationsToStores();
                            break;
                        case "UnlocateAllStores" :
                            sender.unlocateAllStores();
                            break;
                        case "UnassignAllLocations" :
                            sender.unassignAllLocations();
                            break;
                        case "AssignPath" :
                            sender.assignAllPaths();
                            break;
                        case "emplacementParcours":
                            sender.sendParcours(inputFromClient);
                            break;
                        default: System.out.println("Not Comparable");
                    }
                    /*
                    switch(str)
                    {
                        case  "Store:all":
                            sendAllStore();
                            break;
                        default : sendProducts(str);
                    }*/
                }else
                {
                    this.running = false;
                    this.server.releaseConnection(this.database);
                    this.database = null;
                }
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * to get the location
     * @return location
     */
    public EmplacementDAO geteDAO()
    {
        return this.eDAO;
    }

    /**
     * to get the store category
     * @return the category
     */
    public CategorieBoutiqueDAO getCbDAO() {
        return cbDAO;
    }

    public ClientDAO getcDAO() {
        return cDAO;
    }


    /**
     * to read from the server
     * @return what have been read
     * @throws IOException
     */
    public String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = this.reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}
