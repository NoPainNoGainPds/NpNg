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

/**
 * Main class, to launch the server
 */
public class Main {
    public static void main(String[] args) {
        Database db = null;
        //lancement de l'ecout des connections et création de threads
            //creation du server
            Server server = new Server();
            server.start();
    }
}
