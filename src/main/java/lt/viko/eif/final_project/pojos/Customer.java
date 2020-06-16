package lt.viko.eif.final_project.pojos;

/**
 * Customer class represents a customer in the database.
 */
public class Customer {
    private int Id;
    private String name;
    private String countryCode;
    private String wikiURL;

    /**
     * Gets customer id.
     * @return rocket id
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets customer id.
     * @param id launch pad id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Gets customer name.
     * @return customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets customer name.
     * @param name customer name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets country code of a customer.
     * @return country code of a customer
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets country code of a customer.
     * @param countryCode country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Gets wikipedia link about a customer.
     * @return wikipedia link
     */
    public String getWikiURL() {
        return wikiURL;
    }

    /**
     * Sets wikipedia link about a customer.
     * @param wikiURL wikipedia link
     */
    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }
}
