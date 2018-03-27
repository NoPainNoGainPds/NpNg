package controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Boutique;
import model.InputFromClient;
import model.Produit;
import utils.daoUtils.*;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 */
public class Client extends Thread {
    private Socket skt;
    private Connection database;
    private Server server;
    private boolean running = true;
    private ObjectMapper mapper;
    //create DAO with Database connexion
    private BoutiqueDAO bDAO;
    private CategorieBoutiqueDAO cbDAO;
    private CategorieProduitDAO cpDAO;
    private EmplacementDAO eDAO;
    private FournisseurDAO fDAO;
    private ProduitDAO pDAO;
    private StockSortieDAO ssDAO;

    private Sender sender;

    private DataInputStream reader = null;
    private BufferedOutputStream writer = null;
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
    }
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
                            sender.sendAllStore();//et la je renvoi le liste de toutes le boutiques
                        else if(inputFromClient.getId()==-2)
                            sender.sendStoreWhoSale(inputFromClient.getRef());
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
    public EmplacementDAO geteDAO()
    {
        return this.eDAO;
    }
    public CategorieBoutiqueDAO getCbDAO() {
        return cbDAO;
    }


    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = this.reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}
