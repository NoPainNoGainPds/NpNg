package utils;


import java.sql.*;

public class Database {
    private String url,user,password;
    private Connection con = null;
    /*
     * Chargement du driver de connexion a la base de données.
     * ceci ne s'execute qu'une seul fois
     */
    static
    {
        try {

            Class.forName(Constants.DRIVER_NAME).newInstance();
            //DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
            System.out.println("*** Driver loaded.");
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
    /*
     * Construteur de la class database
     */
    public Database()
    {

    }
    public ResultSet getListeTable()
    {
        try
        {
            Statement stmt = this.con.createStatement();
            ResultSet res =  stmt.executeQuery("SHOW TABLES;");
            stmt.close();
            return res;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getValueTable(String table)
    {
        try
        {
            Statement stmt = this.con.createStatement();
            ResultSet res =  stmt.executeQuery("SELECT * FROM "+table+";");
            stmt.close();
            return (res);
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void connect() throws SQLException
    {
        if(this.con==null)
            this.con = DriverManager.getConnection(this.url,this.user,this.password);
    }
    public Connection getConnection()
    {
        return this.con;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}