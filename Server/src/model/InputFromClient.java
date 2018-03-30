package model;

/**
 * Represents the input from the client
 */
public class InputFromClient {
    /**
     * Name of the input
     */
    private String name;
    /**
     * id of the iput
     */
    private int id;
    /**
     * Reference of the input
     */
    private String ref;

    /**
     * Constructor
     */
    public InputFromClient()
    {
    }

    /**
     * get the input name
     * @return input name
     */
    public String getName() {
        return name;
    }

    /**
     * Set input name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get input id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * set inout id
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get input reference
     * @return the reference
     */
    public String getRef() {
        return ref;
    }

    /**
     * set input reference
     * @param ref the new reference
     */
    public void setRef(String ref) {
        this.ref = ref;
    }
}
