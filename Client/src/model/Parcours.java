package model;

public class Parcours {
    private String nameParcours;
    private String IdParcours;


    public String getName() {
        return nameParcours;
    }

    public void setName(String name) {
        this.nameParcours = nameParcours;
    }

    public String getId() {
        return IdParcours;
    }

    public void setId(String IdParcours) {
        IdParcours = IdParcours;
    }


    public String toString()
    {
        return "nom :"+this.nameParcours;
    }
}
