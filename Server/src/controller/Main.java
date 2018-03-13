package controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.Database;
import model.ConnectionPool;
import utils.Constants;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Database db = null;
        //lancement de l'ecout des connections et création de threads
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();

            db = objectMapper.readValue(new File("settings"), Database.class);
            ConnectionPool listeCo = new ConnectionPool(db);

            //creation du server
            ServerSocket serverSocket = new ServerSocket(Constants.PORT);
            //liste de tous les clients
            ArrayList<Client> arraySock = new ArrayList<>();

            int nbConnecté = 0;
            int i = 0;
            while(true)
            {
                Socket skt = serverSocket.accept();
                if(skt!=null)
                {
                    Client sktClient = new Client(skt,listeCo.getConnectionFromPool());
                    arraySock.add(sktClient);
                    nbConnecté+=1;
                    Thread t = new Thread(sktClient);
                    t.start();
                }
            }
        }  catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
