package model;

public class Profil {
    private String name;
    private String Id;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String toString()
    {
        return "nom :"+this.name+" ; desc : "+this.desc;
    }
}
