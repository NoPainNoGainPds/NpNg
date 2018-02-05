package controller;


import vue.Fenetre;

public class Main{
    public static void main(String[] args)
    {
        Fenetre fen = new Fenetre("Prototype de Sylvain le truand");
        javax.swing.SwingUtilities.invokeLater(fen);
    }
}
