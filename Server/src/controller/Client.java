package controller;

import java.net.Socket;
import java.sql.Connection;

public class Client extends Thread {
    private Socket skt;
    private Connection database;
    private boolean running = true;
    public Client(Socket skt, Connection sql)
    {
        this.skt = skt;
        this.database = sql;
    }
    @Override
    public void run()
    {
        while(this.running)
        {
            //recuperation des info
            System.out.println("running");
        }
    }
}
