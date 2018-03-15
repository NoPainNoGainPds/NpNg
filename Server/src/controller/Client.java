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

public class Client extends Thread {
    private Socket skt;
    private Connection database;
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

    private BufferedInputStream reader = null;
    private BufferedOutputStream writer = null;
    public Client(Socket skt, Connection sql)
    {
        this.skt = skt;
        this.database = sql;
        this.bDAO = new BoutiqueDAO(this.database);
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
            this.reader = new BufferedInputStream(this.skt.getInputStream());
            this.writer = new BufferedOutputStream(this.skt.getOutputStream());
            this.mapper = new ObjectMapper();
            while (this.running) {
                //recuperation des info
                //System.out.println("running");
                if (!this.skt.isClosed()) {
                    String str = read();
                    InputFromClient inputFromClient = this.mapper.readValue(str, InputFromClient.class);
                    switch(inputFromClient.getName())
                    {
                        case "Store" :
                            sendAllStore();
                            break;
                        case "Product" :
                            sendProducts(inputFromClient.getId());
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

    private void sendAllStore()
    {
        try {
            ArrayList<Boutique> objReturn = this.bDAO.findFromReference(this);
            System.out.println(objReturn.size());
            for(Boutique b : objReturn)
            {
                String s = "";
                s =this.mapper.writeValueAsString(b);
                this.writer.write(s.getBytes(),0,s.length());
                this.writer.write("#".getBytes());
                this.writer.flush();
            }
            this.writer.write("null".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    private void sendProducts(int id)
    {
        if(id!=-1)
        {
            try {
                ArrayList<Produit> liste = this.pDAO.findFromReference(id);
                for (Produit p : liste) {
                    String send = "";
                    send = this.mapper.writeValueAsString(p);
                    this.writer.write(send.getBytes(), 0, send.length());
                    this.writer.write("#".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null".getBytes());
                this.writer.flush();
            }catch (JsonMappingException e) {
                e.printStackTrace();
            } catch(IOException e)
            {
                e.printStackTrace();
            }
        }
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
