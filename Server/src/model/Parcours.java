package model;

import java.util.List;

public class Parcours {

    private int id;
    private List<Boutique> storeList;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Boutique> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Boutique> storeList) {
        this.storeList = storeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
