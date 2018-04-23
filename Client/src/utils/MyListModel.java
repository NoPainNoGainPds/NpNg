package utils;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class qui permet d'utiliser un model simple pour les JList
 * @param <T>
 */
public class MyListModel<T>  extends AbstractListModel {
    private ArrayList<T> liste;

    /**
     * Construsteur du model
     * @param liste
     */
    public MyListModel(ArrayList<T> liste) {
        this.liste = liste;
    }

    /**
     * renvoi le nombre d'element dans la liste
     * @return int
     */
    public int getSize() {
        return liste.size();
    }

    /**
     * recuperer un element de la liste
     * @param index
     * @return <T>
     */
    public T getElementAt(int index) {
        return this.liste.get(index);
    }

    /**
     * ajout un element a la liste
     * @param elem
     * @return void
     */
    public void addElement(T elem)
    {
        this.liste.add(elem);
    }
}