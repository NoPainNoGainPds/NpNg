package controller;

import utils.daoUtils.*;

import java.net.Socket;
import java.sql.Connection;

public class Client extends Thread {
    private Socket skt;
    private Connection database;
    private boolean running = true;
    //create DAO with Database connexion
    private BoutiqueDAO bDAO;
    private CategorieBoutiqueDAO cbDAO;
    private CategorieProduitDAO cpDAO;
    private EmplacementDAO eDAO;
    private FournisseurDAO fDAO;
    private ProduitDAO pDAO;
    private StockSortieDAO ssDAO;
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
        while(this.running)
        {
            //recuperation des info
            //System.out.println("running");
            try
            {

            }catch(Exception e)
            {

            }
        }
    }
}
