package controller;


import vue.Fenetre;

public class Main{
    public static void main(String[] args)
    {
        Fenetre fen = new Fenetre("Prototype");
        javax.swing.SwingUtilities.invokeLater(fen);
    }
}
