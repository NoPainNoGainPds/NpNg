package controller;


import utils.*;
import vue.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args)
    {
        Constants.conServ = new ConnectionServer();
        Fenetre fen = new Fenetre("Client NPNG");
        javax.swing.SwingUtilities.invokeLater(fen);
    }
}
