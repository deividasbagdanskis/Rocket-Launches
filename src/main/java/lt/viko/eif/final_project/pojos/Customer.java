package lt.viko.eif.final_project.pojos;

/**
 * 
 */

public class Customer {
    private int Id;
    private String name;
    private String coutryCode;
    private String wikiURL;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return coutryCode;
    }

    public void setCountryCode(String coutryCode) {
        this.coutryCode = coutryCode;
    }

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }
}
