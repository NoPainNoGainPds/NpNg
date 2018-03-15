package utils;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;

public class ConnectionServer {
    private Socket server;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private ObjectMapper mapper;
    public ConnectionServer()
    {
        try {

            this.server = new Socket(Constants.host, Constants.port);
            this.writer = new PrintWriter(this.server.getOutputStream());
            this.reader = new BufferedInputStream(this.server.getInputStream());
            this.mapper = new ObjectMapper();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public void send(Object tosend)
    {
        if(this.writer!=null)
        {
            this.writer.print(tosend);
            this.writer.flush();
        }
    }
    public Object recieve(Class className)
    {
        try{
            //System.out.println(read());
            Object obj = this.mapper.readValue(this.reader,className);
            return obj;
        }catch(IOException e) {
            e.printStackTrace();
            return null;
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
