package utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;

public class ConnectionServer {
    private Socket server;
    public ConnectionServer()
    {
        try {

            this.server = new Socket(Constants.host, Constants.port);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public void send(Object tosend)
    {

    }
    public Object recieve()
    {
        return null;
    }
}
