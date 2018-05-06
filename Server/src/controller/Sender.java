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

/**
 * Represents the sender
 */
public class Sender {
    /**
     * The output stream
     */
    private BufferedOutputStream writer;
    /**
     * The object mapper
     */
    private ObjectMapper mapper;
    /**
     * The store
     */
    private BoutiqueDAO bDAO;
    /**
     * The store category
     */
    private CategorieBoutiqueDAO cbDAO;
    /**
     * The product category
     */
    private CategorieProduitDAO cpDAO;
    /**
     * The location
     */
    private EmplacementDAO eDAO;
    /**
     * The provider
     */
    private FournisseurDAO fDAO;
    /**
     * The product
     */
    private ProduitDAO pDAO;
    /**
     * The output storage
     */
    private StockSortieDAO ssDAO;
    /**
     * The input storage
     */
    private StockEntreeDAO esDAO;
    /**
     * The Client
     */
    private ClientDAO cDAO;
    /**
     * The fee
     */
    private RedevanceDAO rDAO;
    /**
     *
     */
    private ProfilDAO profDAO;

    /**
     * Constructor
     * @param database the connection to the database
     * @param mapper the object mapper
     * @param writer the output stream
     * @param client the client
     */
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
        this.rDAO = new RedevanceDAO(database);
        this.cDAO = client.getcDAO();
        this.profDAO = new ProfilDAO(database);
        this.mapper = mapper;
        this.mapper.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        this.writer = writer;
    }

    /**
     * Method to send all the store which are in the database
     */
    public void sendAllStore()
    {
        try {
            ArrayList<Boutique> objReturn = this.bDAO.findFromReference();
            System.out.println(objReturn.size());
            for(Boutique b : objReturn)
            {
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

    /**
     * Method to send the products if id is not equal to -1
     * @param id the id
     */
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

    public void sendClient(int id)
    {
        if(id==-1)
        {
            try {
                ArrayList<ClientModel> liste = this.cDAO.findFromReference();
                for (ClientModel p : liste) {
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

    /**
     * Method to send the stores which sell the product productName
     * @param productName name of the product
     */
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

    /**
     * Method to send what is getting out from storages
     * @param inputFromClient what the client wants
     */
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

    /**
     * Method to send what is entering storage
     * @param inputFromClient the input from the client
     */
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
    /**
     * Methode to insert product from client
     * @param product the product to insert
     */
    public void insertProduct(Produit product)
    {
        try {
            Boolean b = this.pDAO.create(product);
            this.mapper.writeValue(this.writer, b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
            System.out.println("creation produit");
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void updateProfil(int id)
    {
        try{
            Boolean b = false;
            if(id==-1)
            {
                b = this.cDAO.delAllProfil();
            }else if (id==1)
            {
                b = this.cDAO.attrProf();
            }
            this.mapper.writeValue(this.writer,b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void sendProfilClient(int id)
    {
        try
        {
            ArrayList<String> liste = this.profDAO.getProfilFromClient(id);
            for(String r : liste)
            {
                this.mapper.writeValue(this.writer,r);
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
    public void sendAllfees()
    {
        try {
            ArrayList<Redevance> objReturn = this.rDAO.findFromReference();
            System.out.println(objReturn.size());
            for(Redevance r : objReturn)
            {
                this.mapper.writeValue(this.writer,r);
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

    public void sendFee(InputFromClient inputFromClient)
    {
        try {
            if (!inputFromClient.getRef().equals("")) {
                ArrayList<Redevance> liste = this.rDAO.findFromReference(inputFromClient.getId(),Integer.parseInt(inputFromClient.getRef()));
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

    public void sendStoreWhoPay()
    {
        try {
            ArrayList<Boutique> objReturn = this.bDAO.findWhoPay();
            System.out.println(objReturn.size());
            for(Boutique b : objReturn)
            {
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

    public void insertFee(InputFromClient valeurici)
    {
        Redevance fee = null;
        try {
            Boolean b = this.rDAO.create(fee);
            this.mapper.writeValue(this.writer, b);
            this.writer.write("\n".getBytes());
            this.writer.flush();
            System.out.println("creation redevance");
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void dellAllProfil(){
        this.cDAO.delAllProfil();
    }

}
