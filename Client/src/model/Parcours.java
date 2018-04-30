package model;

import java.util.ArrayList;

public class Parcours {
    private String nameParcours;
    private String idParcours;
    private ArrayList<Boutique> listeEmplacement;
    private int timeParcours;


    public String getName() {
        return nameParcours;
    }

    public void setName(String nameParcours) {
        this.nameParcours = nameParcours;
    }

    public String getId() {
        return idParcours;
    }

    public void setId(String IdParcours) {
        idParcours = idParcours;
    }

    public  ArrayList<Boutique> getList(){ return listeEmplacement; }

    public String getTime() {
        return nameParcours;
    }

    public void setTime(int timeParcours) {
        this.timeParcours = timeParcours;
    }

    public String toString()
    {
        return "Le nom du parcours type est :"+this.nameParcours;
    }
}
