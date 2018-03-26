package controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import utils.daoUtils.*;

import java.io.BufferedOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class Sender {
    private BufferedOutputStream writer;
    private ObjectMapper mapper;
    private BoutiqueDAO bDAO;
    private CategorieBoutiqueDAO cbDAO;
    private CategorieProduitDAO cpDAO;
    private EmplacementDAO eDAO;
    private FournisseurDAO fDAO;
    private ProduitDAO pDAO;
    private StockSortieDAO ssDAO;
    private StockEntreeDAO esDAO;
    public Sender(Connection database, ObjectMapper mapper,BufferedOutputStream writer,Client client)
    {
        this.bDAO = new BoutiqueDAO(database,client);
        this.cbDAO = new CategorieBoutiqueDAO(database);
        this.cpDAO = new CategorieProduitDAO(database);
        this.eDAO = new EmplacementDAO(database);
        this.fDAO = new FournisseurDAO(database);
        this.pDAO = new ProduitDAO(database);
        this.ssDAO = new StockSortieDAO(database);
        this.esDAO = new StockEntreeDAO(database);
        this.mapper = mapper;
        this.mapper.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        this.writer = writer;
    }
    public void sendAllStore()
    {
        try {
            ArrayList<Boutique> objReturn = this.bDAO.findFromReference();
            System.out.println(objReturn.size());
            for(Boutique b : objReturn)
            {
                System.out.println("boutique");
                this.mapper.writeValue(this.writer,b);
                this.writer.write("\n".getBytes());
                this.writer.flush();
            }
            this.writer.write("null\n".getBytes());
            this.writer.flush();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void sendProducts(int id)
    {
        if(id!=-1)
        {
            try {
                ArrayList<Produit> liste = this.pDAO.findFromReference(id);
                for (Produit p : liste) {
                    this.mapper.writeValue(this.writer,p);
                    this.writer.write("\n".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }catch (JsonMappingException e) {
                e.printStackTrace();
            } catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void sendStoreWhoSale(String productName)
    {
        try {
            if (!productName.equals("")) {
                Integer[] liste = this.bDAO.findWhoSale(productName);
                for(int i = 0;i<liste.length;i++) {
                    this.mapper.writeValue(this.writer,liste[i]);
                    this.writer.write("\n".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
            else
            {
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
        }catch(JsonMappingException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void sendSortieStock(InputFromClient inputFromClient)
    {
        try {
            if (!inputFromClient.getRef().equals("")) {
                ArrayList<StockSortie> liste = this.ssDAO.findFromReference(inputFromClient.getId(),Integer.parseInt(inputFromClient.getRef()));
                for(int i = 0;i<liste.size();i++) {
                    this.mapper.writeValue(this.writer,liste.get(i));
                    this.writer.write("\n".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
            else
            {
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
        }catch(JsonMappingException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    public void sendEntreeStock(InputFromClient inputFromClient)
    {
        try {
            if (!inputFromClient.getRef().equals("")) {
                ArrayList<StockEntree> liste = this.esDAO.findFromReference(inputFromClient.getId(),Integer.parseInt(inputFromClient.getRef()));
                for(int i = 0;i<liste.size();i++) {
                    this.mapper.writeValue(this.writer,liste.get(i));
                    this.writer.write("\n".getBytes());
                    this.writer.flush();
                }
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
            else
            {
                this.writer.write("null\n".getBytes());
                this.writer.flush();
            }
        }catch(JsonMappingException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
