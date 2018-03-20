package utils;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;

public class ConnectionServer {
    private Socket server;
    private PrintWriter writer = null;
    private DataInputStream reader = null;
    private ObjectMapper mapper;
    public ConnectionServer()
    {
        try {

            this.server = new Socket(Constants.host, Constants.port);
            this.writer = new PrintWriter(this.server.getOutputStream());
            this.reader = new DataInputStream(this.server.getInputStream());
            this.mapper = new ObjectMapper();
            this.mapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
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

    /**
     * Method who read the socket input stream and map the byte[] into Object
     * @param className
     * @return
     */
    public Object recieve(Class className)
    {
        ObjectReader jSonreader = this.mapper.readerFor(className).without(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        try{
                Object obj = jSonreader.readValue(this.reader.readLine());
                return obj;
            }catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096*2];
        stream = this.reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}
