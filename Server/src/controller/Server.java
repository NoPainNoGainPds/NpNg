package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.Database;
import model.ConnectionPool;
import utils.Constants;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

public class Server {
    private ConnectionPool listeCo;
    private ArrayList<Client> arraySock;
    private ServerSocket serverSocket;
    private int nbConnecté;
    public Server()
    {
        Database db = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            db = objectMapper.readValue(new File("settings"), Database.class);
            this.listeCo = new ConnectionPool(db);
            //creation du server
            this.serverSocket = new ServerSocket(Constants.PORT);
            //liste de tous les clients
            this.arraySock = new ArrayList<>();

            this.nbConnecté = 0;
            int i = 0;
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void start()
    {
        while(true)
        {
            try {
                Socket skt = serverSocket.accept();
                if (skt != null) {
                    Client sktClient = new Client(skt, this.listeCo.getConnectionFromPool(),this);//creation du client ici
                    arraySock.add(sktClient);
                    this.nbConnecté += 1;
                    Thread t = new Thread(sktClient);
                    t.start();
                }
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void releasConnection(Connection con)
    {
        this.listeCo.returnConnectionToPool(con);
    }
}
