package lt.viko.eif.final_project.pojos;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;

/**
 * Rocket class represents a rocket in the database.
 */
public class Rocket {
    private int Id;
    private String name;
    private String manufacturer;
    private String type;
    private String countryOfOrigin;
    private double height;
    private double diameter;
    private int mass;
    private int numberOfStages;
    private int payloadToLEO;
    private int payloadToGTO;
    private String wikiURL;
    private List<Stage> stages = new ArrayList<>();
    private List<Link> links = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public Rocket() {
    }

    /**
     * Gets rocket id.
     * @return rocket id
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets rocket id.
     * @param id rocket id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Gets rocket name.
     * @return rocket name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets rocket name.
     * @param name rocket name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets rocket manufacturer.
     * @return rocket manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets rocket manufacturer.
     * @param manufacturer rocket manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets rocket type.
     * @return rocket type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets rocket type.
     * @param type rocket type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets rocket's country of origin
     * @return country of origin
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * Sets rocket's country of origin
     * @param countryOfOrigin country of origin
     */
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    /**
     * Gets rocket height.
     * @return rocket height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets rocket height.
     * @param height rocket height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Gets rocket diameter.
     * @return rocket diameter
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * Sets rocket diameter.
     * @param diameter rocket diameter
     */
    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    /**
     * Gets rocket mass.
     * @return rocket mass
     */
    public int getMass() {
        return mass;
    }

    /**
     * Sets rocket mass.
     * @param mass rocket mass
     */
    public void setMass(int mass) {
        this.mass = mass;
    }

    /**
     * Gets number of stages in a rocket.
     * @return number of stages
     */
    public int getNumberOfStages() {
        return numberOfStages;
    }

    /**
     * Sets number of stages in a rocket.
     * @param numberOfStages number of stages
     */
    public void setNumberOfStages(int numberOfStages) {
        this.numberOfStages = numberOfStages;
    }

    /**
     * Gets the value of payloadToLEO property.
     * @return payload to LEO in kilograms
     */
    public int getPayloadToLEO() {
        return payloadToLEO;
    }

    /**
     * Sets the value of payloadToLEO property.
     * @param payloadToLEO payload in kilograms
     */
    public void setPayloadToLEO(int payloadToLEO) {
        this.payloadToLEO = payloadToLEO;
    }

    /**
     * Gets the value of payloadToGTO property.
     * @return payload to GTO in kilograms
     */
    public int getPayloadToGTO() {
        return payloadToGTO;
    }

    /**
     * Sets the value of payloadToGTO property.
     * @param payloadToGTO payload in kilograms
     */
    public void setPayloadToGTO(int payloadToGTO) {
        this.payloadToGTO = payloadToGTO;
    }

    /**
     * Gets a list of Stage objects that are assigned to a rocket.
     * @return list of stages
     */
    public List<Stage> getStages() {
        return stages;
    }

    /**
     * Sets the list of stages.
     * @param stages stages
     */
    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    /**
     * Gets a list of Link objects that are assigned to a rocket.
     * @return list of links
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * Gets wikipedia link about a rocket.
     * @return wikipedia URL
     */
    public String getWikiURL() {
        return wikiURL;
    }

    /**
     * Sets wikipedia link about a rocket.
     * @param wikiURL wikipedia URL
     */
    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    /**
     * Sets a list of Link objects that are assigned to a rocket.
     * @param links list of Link objects
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * Adds a link to a list of links.
     * @param url URL of the link
     * @param rel name of link relation to the rocket
     */
    public void addLink(String url, String rel) {
        Link link = Link.fromUri(url).rel(rel).build();
        this.links.add(link);
    }
}
