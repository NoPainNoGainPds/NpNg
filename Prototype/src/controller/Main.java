package controller;



import utils.ConnectionServer;
import utils.Constants;
import utils.Database;
import vue.Fenetre;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args)
    {
        Constants.conServ = new ConnectionServer();
        Fenetre fen = new Fenetre("Prototype Lois");
        javax.swing.SwingUtilities.invokeLater(fen);
    }
}
