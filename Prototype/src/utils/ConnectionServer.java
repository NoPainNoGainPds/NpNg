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

    /**
     * Method who read the socket input stream and map the byte[] into Object
     * @param className
     * @return
     */
    public Object[] recieve(Class className)
    {
        try{
            //System.out.println(read());
            String s = read();
            String[] s2 = s.split("#");
            Object[] objToreturn = new Object[s2.length];
            for(int i=0;i< s2.length;i++)
            {
                if(!s.equals("null")) {
                    Object obj = this.mapper.readValue(s2[i], className);
                    objToreturn[i] = obj;
                    //System.out.println(obj);
                }else
                {
                    objToreturn[i] = null;
                }
            }
            return objToreturn;
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
