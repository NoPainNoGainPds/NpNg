package utils;

import javax.swing.*;
import java.util.ArrayList;

public class MyListModel<T>  extends AbstractListModel {
    private ArrayList<T> liste;

    public MyListModel(ArrayList<T> liste) {
        this.liste = liste;
    }

    public int getSize() {
        return liste.size();
    }

    public Object getElementAt(int index) {
        return this.liste.get(index);
    }
}