package controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Boutique;
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
                if (!this.skt.isClosed())
                    this.running = false;
                    String str = read();
                    System.out.println(str);
                    switch(str)
                    {
                        case  "Store:all":
                            sendAllStore();
                            break;
                        default : System.out.println("not comparable");
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
                this.mapper.writeValue(this.writer,b);
                System.out.println("write : "+b);
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
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
